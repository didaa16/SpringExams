package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Flight;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Flight findFlightByNumber(String number);
}