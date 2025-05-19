package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Course;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findPersonByCoursesAndDateOfBirthBefore(Set<Course> courses, LocalDate dateOfBirthBefore);

    List<Person> findByCourses_Exercices_TitleAndDateOfBirthLessThan(String title, LocalDate dateOfBirth);
}