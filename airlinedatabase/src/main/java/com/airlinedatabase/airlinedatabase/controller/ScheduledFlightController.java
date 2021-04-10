package com.airlinedatabase.airlinedatabase.controller;

import com.airlinedatabase.airlinedatabase.exceptions.RecordNotFoundException;
import com.airlinedatabase.airlinedatabase.exceptions.ScheduledFlightNotFoundException;
import com.airlinedatabase.airlinedatabase.model.Airport;
import com.airlinedatabase.airlinedatabase.model.Schedule;
import com.airlinedatabase.airlinedatabase.model.ScheduledFlight;
import com.airlinedatabase.airlinedatabase.services.AirportService;
import com.airlinedatabase.airlinedatabase.services.FlightService;
import com.airlinedatabase.airlinedatabase.services.ScheduledFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/scheduledFlights")
public class ScheduledFlightController {

    @Autowired
    ScheduledFlightService scheduledFlightService;
    @Autowired
    AirportService airportService;
    @Autowired
    FlightService flightService;

    /*
    Controller za dodavanje letova
    */
    @PostMapping("/add")
    public ResponseEntity<ScheduledFlight> addScheduledFlight(@ModelAttribute ScheduledFlight scheduledFlight,
                                                              @RequestParam(name = "departureAirport") String departAirport,
                                                              @RequestParam(name = "destinationAirport") String destAirport,
                                                              @RequestParam(name = "departureDateTime") String departureTime,
                                                              @RequestParam(name = "arrivalDateTime") String arrivalTime){

        Schedule schedule = new Schedule();
        schedule.setScheduleId(scheduledFlight.getScheduleFlightId());

        try{
            schedule.setDepartAirport((Airport) airportService.viewAirport(departAirport));
        }catch (RecordNotFoundException e){
            return new ResponseEntity("Airport not found", HttpStatus.BAD_REQUEST);
        }

        try {
            schedule.setDestinationAirport((Airport) airportService.viewAirport(destAirport));
        }catch (RecordNotFoundException e){
            return new ResponseEntity("Airport not found", HttpStatus.BAD_REQUEST);
        }

        schedule.setDepartDateTime(departureTime);
        schedule.setArrivalDateTime(arrivalTime);

        try {
            scheduledFlight.setFlight(flightService.viewFlight(scheduledFlight.getScheduleFlightId()));
        }catch (RecordNotFoundException e){
            return new ResponseEntity("Flight not found", HttpStatus.BAD_REQUEST);
        }

        scheduledFlight.setSchedule(schedule);
        scheduledFlight.setAvailableSeats(scheduledFlight.getFlight().getSeatCapacity());

        try {
            return new ResponseEntity<ScheduledFlight>(scheduledFlightService.addScheduledFlight(scheduledFlight),
                    HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error while adding a flight" + e, HttpStatus.BAD_REQUEST);
        }

    }

    /*
    Controller za modifikaciju/update letova
    */
    @PutMapping("/modify")
    public ResponseEntity<ScheduledFlight> updateScheduledFlight(@ModelAttribute ScheduledFlight scheduledFlight){
        ScheduledFlight update = scheduledFlightService.modifyScheduledFlight(scheduledFlight);

        if(update == null){
            return new ResponseEntity("Flight not modified", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            return new ResponseEntity<ScheduledFlight>(update, HttpStatus.OK);
        }

    }

    /*
    Controller za brisanje postojećih letova letova
    */
    @DeleteMapping("/delete")
    public String deleteScheduledFlight(@RequestParam BigInteger flightId) throws RecordNotFoundException{
        return scheduledFlightService.removeScheduledFlight(flightId);
    }

    /*
    Controller za pregled letova po ID-u
    */
    @GetMapping("/search")
    @ExceptionHandler(ScheduledFlightNotFoundException.class)
    public ResponseEntity<ScheduledFlight> viewScheduledFlight(@RequestParam BigInteger flightId) throws RecordNotFoundException{
        ScheduledFlight searchScheduledFlight = scheduledFlightService.viewScheduledFlight(flightId);

        if(searchScheduledFlight == null)
            return new ResponseEntity("Flight not found", HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<ScheduledFlight>(searchScheduledFlight, HttpStatus.OK);

    }

    /*
    Controller za pregled svih letova
    */

    @GetMapping("/viewAll")
    public Iterable<ScheduledFlight> viewAllScheculedFlights(){
        return scheduledFlightService.viewAllScheduledFlights();
    }
}
