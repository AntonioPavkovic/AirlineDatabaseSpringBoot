package com.airlinedatabase.airlinedatabase.controller;

import com.airlinedatabase.airlinedatabase.exceptions.RecordAlreadyPresentException;
import com.airlinedatabase.airlinedatabase.exceptions.RecordNotFoundException;
import com.airlinedatabase.airlinedatabase.model.Flight;
import com.airlinedatabase.airlinedatabase.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@CrossOrigin("http://127.0.0.1:8999/")
@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @PostMapping("/addFlights")
    @ExceptionHandler(RecordAlreadyPresentException.class)
    public void addFlight(Flight flight){
        flightService.addFlight(flight);
    }

    @GetMapping("/allFlights")
    public Iterable<Flight> viewAllFlights() {
        return flightService.viewAllFlights();
    }

    @GetMapping("/viewFlights/{id}")
    @ExceptionHandler(RecordNotFoundException.class)
    public Flight viewFlightById(@PathVariable("id") BigInteger flightId){
        return flightService.viewFlight(flightId);
    }

    @PutMapping("/updateFlights")
    @ExceptionHandler(RecordNotFoundException.class)
    public void updateFlight(@RequestBody Flight flight){
        flightService.modifyFlight(flight);
    }

    @DeleteMapping("/deleteFlights/{id}")
    @ExceptionHandler(RecordNotFoundException.class)
    public void deleteFlight(@PathVariable("id") BigInteger flightId){
        flightService.deleteFlight(flightId);
    }

}
