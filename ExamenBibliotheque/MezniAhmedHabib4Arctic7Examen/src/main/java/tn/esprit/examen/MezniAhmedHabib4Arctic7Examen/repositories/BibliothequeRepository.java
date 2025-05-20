package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Bibliotheque;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.TypeAction;

import java.util.Collection;
import java.util.List;

public interface BibliothequeRepository extends JpaRepository<Bibliotheque, Long> {
    Bibliotheque findBibliothequesByLibelle(String libelle);




}