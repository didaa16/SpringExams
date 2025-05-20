package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Action;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.TypeAction;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Long> {
    List<Action> findByDateIn(Collection<LocalDate> dates);

    @Query("SELECT a FROM Livre l JOIN l.Actions a " +
            "WHERE a.type = :type " +
            "AND l.titre = :titre " +
            "AND l.auteur = :auteur")
    List<Action> listerActionsParTypeEtLivreTitreEtLivreAuteur (TypeAction type, String titre, String auteur);
//    List<Exercice> retrieveRigidEx(@Param("startDate") LocalDate startDate,
//                                   @Param("endDate") LocalDate endDate);
}