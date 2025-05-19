package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Course;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Exercice;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Level;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findCourseByLevelAndPerson_NameAndDateBefore(Level level, String personName, LocalDate dateBefore);

    Course findCourseByNum(Integer num);
}