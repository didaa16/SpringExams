package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Airline;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Flight;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.services.IServices;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("examen")
@RestController
public class ClientRestController {
    private final IServices services;
    @PostMapping("/addAirline")
    public Airline addAirline(@RequestBody Airline airline){
        return  services.addAirline(airline);
    }

    @PostMapping("/addFlightWithPassengers")
    public Flight addFlightWithPassengers(@RequestBody Flight flight){
        return  services.addFlightWithPassengers(flight);
    }

    @PostMapping("assign-flights/{flightNum}/{codeIATA}")
    public Airline assignFlightsToAirline(@PathVariable("flightNum") List<String> flightNumbers, @PathVariable("codeIATA") String codeIATA) {
        return services.assignFlightsToAirline(flightNumbers, codeIATA);
    }


    @GetMapping("/compensation-miles")
    public List<String> getAirlineByCompensationsMiles() {
        return services.getAirlineByCompensationsMiles();
    }

    @PostMapping("/compensate-miles")
    public void compensationMilles() {
        services.compensationMilles();
    }

}
