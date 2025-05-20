package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Airline;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Flight;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Passenger;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Status;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories.AirlineRepository;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories.FlightRepository;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories.PassengerRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServicesImpl implements IServices {

    private final AirlineRepository airlineRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;

    @Override
    public Airline addAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    @Override
    @Transactional
    public Flight addFlightWithPassengers(Flight flight) {
        Set<Passenger> p = flight.getPassengers();
        for (Passenger ps: p) {
            ps.setFlight(flight);
            passengerRepository.save(ps);
        }
        return flightRepository.save(flight);
    }

    @Override
    @Transactional
    public Airline assignFlightsToAirline(List<String> numberOfFlights, String codeIATA) {
        Airline airline = airlineRepository.findAirlineByCodeIATA(codeIATA);
        for (String s: numberOfFlights){
            Flight flight = flightRepository.findFlightByNumber(s);
            flight.setAirline(airline);
            airline.getFlights().add(flight);
            flightRepository.save(flight);
        }
        return airlineRepository.save(airline);
    }

    @Scheduled(cron = "0 */2 * * * *")
    @Override
    public void getPastFlights() {
        List<Flight>flights = flightRepository.findAll();
        System.out.println(" \n Flight with estimated departure past are : \n");
        for (Flight f : flights){
            if (f.getEstimatedDeparture().before(new Date())){
                log.info(f.getNumber() + ".\n");
            }
        }
    }

    @Override
    @Transactional
    public void compensationMilles() {
        List<Flight> flights = flightRepository.findAll();
        for (Flight flight : flights) {
            boolean isCompensated = false;
            float compensation = 0;

            if (flight.getStatus() == Status.DELAYED && flight.getRealDeparture() != null && flight.getEstimatedDeparture() != null) {
                long delayMillis = flight.getRealDeparture().getTime() - flight.getEstimatedDeparture().getTime();
                long delayHours = delayMillis / (1000 * 60 * 60);

                if (delayHours >= 15) {
                    compensation = (2f / 5f) * flight.getPrice();
                    isCompensated = true;
                } else if (delayHours >= 5) {
                    compensation = (1f / 5f) * flight.getPrice();
                    isCompensated = true;
                }
            }
            else if (flight.getStatus() == Status.CANCELED) {
                compensation = (3f / 5f) * flight.getPrice();
                isCompensated = true;
            }

            if (isCompensated) {
                for (Passenger passenger : flight.getPassengers()) {
                    passenger.setCompensationMiles(passenger.getCompensationMiles() + compensation);
                    passengerRepository.save(passenger);
                }
                flight.setStatus(Status.COMPENSATED);
                flightRepository.save(flight);
            }
        }
    }

    @Override
    @Transactional
    public List<String> getAirlineByCompensationsMiles() {
        return airlineRepository.getAirlineByCompensationsMiles();
    }


//    private final IClientRepository clientRepository;
//
//    @Override
//    public Client add(Client client) {
//        return clientRepository.save(client);
//    }

//   @Scheduled(cron = "*/15 * * * * *")
//    @Override
//    public void test() {
//        log.info("testing");
//    }
}
