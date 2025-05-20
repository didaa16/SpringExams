package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.services;

import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Airline;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Flight;

import java.util.List;

public interface IServices {
    Airline addAirline(Airline airline);
    Flight addFlightWithPassengers (Flight flight);
    Airline assignFlightsToAirline(List<String> numberOfFlights, String codeIATA);
    void getPastFlights();
    void compensationMilles();
    List<String> getAirlineByCompensationsMiles();
}
