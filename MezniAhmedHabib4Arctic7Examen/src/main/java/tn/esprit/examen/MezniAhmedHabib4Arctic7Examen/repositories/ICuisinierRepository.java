package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Cuisinier;

import java.util.List;

@Repository
public interface ICuisinierRepository extends JpaRepository <Cuisinier, Integer> {
}
