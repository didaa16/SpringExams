package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Client;

public interface IClientRepository extends JpaRepository<Client, Long> {
}