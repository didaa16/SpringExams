package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.*;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories.CourseRepository;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories.ExerciceRepository;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories.IClientRepository;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories.PersonRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServicesImpl implements IServices {

    private final IClientRepository clientRepository;
    private final PersonRepository personRepository;
    private final CourseRepository courseRepository;
    private final ExerciceRepository exerciceRepository;
        @Override
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    @Transactional
    public Course addCourseAssignCoach(Course course, Long personID) {
        Person p = personRepository.findById(personID).orElse(null);
        course.setPerson(p);
        return courseRepository.save(course);
    }

    @Override
    public List<Course> retrieveCourse(Level level, String name) {
        return courseRepository.findCourseByLevelAndPerson_NameAndDateBefore(level, name, LocalDate.now());
    }

    @Override
    @Transactional
    public Course assignAthleteCourse(Long personID, Integer num) {
        Person person = personRepository.findById(personID).orElse(null);
        Course course = courseRepository.findCourseByNum(num);
        if (person != null){
            if (course.getPersons().contains(person))
                log.info("Person already in the course");
            else if (person.getNbMounthsTraining() < 12 && course.getLevel() == Level.ADVANCED)
                log.info("Person cannot access to beginner courses");
            else
                course.getPersons().add(person);
        }
        else {
            log.info("Person not found");
        }
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public List<Person> retrieveAthlete(String title) {
        LocalDate ageLimit = LocalDate.now().minusYears(20);
        return personRepository.findByCourses_Exercices_TitleAndDateOfBirthLessThan(title, ageLimit);
    }

    @Override
    public List<Exercice> retrieveRigidEx(LocalDate startDate, LocalDate endDate) {
        return exerciceRepository.retrieveRigidEx(startDate, endDate);
    }

    @Scheduled(cron = "*/2 * * * * *")
    @Override
    @Transactional
    public void updateNbRept() {
        int dureeTotale = 0;
        int courseDuration = 0;
        int exerciceBreakTime = 0;
        int exerciceRepetition = 0;
        List<Course> courses = courseRepository.findAll();
        for (Course c : courses) {
            for (Exercice e : c.getExercices()){
                courseDuration = e.getDuration();
                exerciceBreakTime = e.getBreakTime();
                exerciceRepetition = e.getNbrOfRepetitions();
                dureeTotale += ((courseDuration+exerciceBreakTime) * exerciceRepetition);
            }
        }
        log.info(String.valueOf(dureeTotale));
    }


//   @Scheduled(cron = "*/15 * * * * *")
//    @Override
//    public void test() {
//        log.info("testing");
//    }
}
