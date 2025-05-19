package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.services;

import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.*;

import java.time.LocalDate;
import java.util.List;

public interface IServices {
    Person addPerson(Person person);
    Course addCourseAssignCoach(Course course, Long personID);
    List<Course> retrieveCourse(Level level , String name);
    Course assignAthleteCourse(Long personID, Integer num);
    List<Person> retrieveAthlete(String title);
    List<Exercice> retrieveRigidEx(LocalDate startDate, LocalDate endDate);
    void updateNbRept();
}
