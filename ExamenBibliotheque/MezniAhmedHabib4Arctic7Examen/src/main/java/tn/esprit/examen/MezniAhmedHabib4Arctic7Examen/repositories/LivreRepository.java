package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Livre;

import java.util.List;

public interface LivreRepository extends JpaRepository<Livre, Long> {
    Livre findLivreByIsbn(String isbn);
    @Query("""
    SELECT l.titre
    FROM Livre l
    JOIN l.Actions a
    WHERE l.bibliotheque.idBibliotheque IN :idsBibliotheque
      AND a.date = (
          SELECT MAX(a2.date)
          FROM Action a2
          WHERE a2 MEMBER OF l.Actions
      )
      AND a.type = tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.TypeAction.EMPRUNT
""")
    List<String> findTitresByBibliothequeIdsWithLastActionEmprunt(@Param("idsBibliotheque") List<Long> idsBibliotheque);

}