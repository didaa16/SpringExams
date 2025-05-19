package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Exercice;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ExerciceRepository extends JpaRepository<Exercice, Long> {
    Set<Exercice> findExerciceByTitle(String title);


    @Query("SELECT e FROM Course c JOIN c.exercices e " +
            "WHERE c.level = 'ADVANCED' " +
            "AND e.breakTime >= 2 " +
            "AND c.date BETWEEN :startDate AND :endDate")
    List<Exercice> retrieveRigidEx(@Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate);

}