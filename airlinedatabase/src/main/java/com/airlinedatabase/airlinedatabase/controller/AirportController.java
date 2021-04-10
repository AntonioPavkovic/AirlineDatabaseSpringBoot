package com.airlinedatabase.airlinedatabase.controller;


import com.airlinedatabase.airlinedatabase.exceptions.RecordAlreadyPresentException;
import com.airlinedatabase.airlinedatabase.exceptions.RecordNotFoundException;
import com.airlinedatabase.airlinedatabase.model.Airport;
import com.airlinedatabase.airlinedatabase.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://127.0.0.1:8999/")
@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired(required = true)
    AirportService airportService;

    @GetMapping("/viewAirports/{id}")
    @ExceptionHandler(RecordNotFoundException.class)
    public Airport viewAirport(@PathVariable("id") String airportId){
        return (Airport) airportService.viewAirport(airportId);
    }

    @GetMapping("/allAirports")
    public Iterable<Airport> viewAllAirports(){
        return airportService.viewAllAirports();
    }

    @PostMapping("/addAirports")
    @ExceptionHandler(RecordAlreadyPresentException.class)
    public void addAirport(@RequestBody Airport airport){
        airportService.addAirport(airport);
    }

    @PutMapping("/updateAirports")
    @ExceptionHandler(RecordNotFoundException.class)
    public void updateAirport(@RequestBody Airport airport){
        airportService.modifyAirport(airport);
    }

    @DeleteMapping("/deleteAirports/{id}")
    @ExceptionHandler(RecordNotFoundException.class)
    public void deleteAirport(@PathVariable("id") String airportId){
        airportService.deleteAirport(airportId);
    }
}
