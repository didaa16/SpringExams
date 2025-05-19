package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, Integer> {
    Client findClientByNomAndPrenom(String nom, String prenom);
}