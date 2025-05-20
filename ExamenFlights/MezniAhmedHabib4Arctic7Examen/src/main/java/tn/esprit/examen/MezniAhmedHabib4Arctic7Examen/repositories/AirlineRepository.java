package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Airline;

import java.util.List;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
    Airline findAirlineByCodeIATA(String codeIATA);
    @Query("SELECT a.name FROM Airline a " +
            "JOIN a.Flights f " +
            "JOIN f.Passengers p " +
            "GROUP BY a.idAirline, a.name " +
            "ORDER BY SUM(p.compensationMiles) DESC")
    List<String> getAirlineByCompensationsMiles();
}