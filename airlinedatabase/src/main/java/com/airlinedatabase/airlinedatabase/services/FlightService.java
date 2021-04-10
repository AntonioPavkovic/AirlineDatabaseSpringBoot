package com.airlinedatabase.airlinedatabase.services;

import com.airlinedatabase.airlinedatabase.exceptions.RecordAlreadyPresentException;
import com.airlinedatabase.airlinedatabase.exceptions.RecordNotFoundException;
import com.airlinedatabase.airlinedatabase.model.Flight;
import com.airlinedatabase.airlinedatabase.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    //dodavanje leta
    public ResponseEntity<Flight> addFlight(Flight flight){
        Optional<Flight> findById = flightRepository.findById(flight.getFlightId());

        try {

            if(!findById.isPresent()){
                flightRepository.save(flight);
                return new ResponseEntity<Flight>(flight, HttpStatus.OK);
            }else
                throw new RecordAlreadyPresentException("Flight with number: " + flight.getFlightId() + " already present");

        } catch (RecordAlreadyPresentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //pregled svih letova
    public Iterable<Flight> viewAllFlights() {
        return flightRepository.findAll();
    }

    //pretrazivanje letova po ID-u
    public Flight viewFlight(BigInteger flightId){
        Optional<Flight> findById = flightRepository.findById(flightId);
        //try{
        if(findById.isPresent()){
            return findById.get();
        }
        else{
            throw new RecordNotFoundException("Flight with ID: " +flightId+ "Doesn't exist");
        }
        /*}catch (RecordNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }*/
    }

    //modifikacija leta
    public Flight modifyFlight(Flight flight){
        Optional<Flight> findById = flightRepository.findById(flight.getFlightId());
        if(findById.isPresent())
            flightRepository.save(flight);
        else
            throw new RecordNotFoundException("Flight with ID: " +flight.getFlightId()+ "Doesn't exist");
        return flight;
    }

    //delete flight
    public String deleteFlight(BigInteger flightId){
        Optional<Flight> findById = flightRepository.findById(flightId);

        if(findById.isPresent()){
            flightRepository.deleteById(flightId);
            return "FlightRemover";
        }
        else
            throw new RecordNotFoundException("Flight with ID: " +flightId+ "doesn't exist");
    }
}
