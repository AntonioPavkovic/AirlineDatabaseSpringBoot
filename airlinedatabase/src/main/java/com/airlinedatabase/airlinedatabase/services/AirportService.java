package com.airlinedatabase.airlinedatabase.services;

import com.airlinedatabase.airlinedatabase.exceptions.RecordAlreadyPresentException;
import com.airlinedatabase.airlinedatabase.exceptions.RecordNotFoundException;
import com.airlinedatabase.airlinedatabase.model.Airport;
import com.airlinedatabase.airlinedatabase.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    Airport airport;

    //pregled svih aerordoma
    public Iterable<Airport> viewAllAirports() {
        return airportRepository.findAll();
    }

    //pregled aerodroma po airportId-u
    /*public ResponseEntity viewAirport(String airportId){
        Optional<Airport> findById = airportRepository.findById(airportId);

        try{

            if(findById.isPresent()){
                return findById.get();
            }

            return new ResponseEntity<Airport>(airport, HttpStatus.OK);

        } catch (RecordNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }*/

    public Object viewAirport(String airportId){
        Optional<Airport> findById = airportRepository.findById(airportId);

        try{

            if(findById.isPresent()){
                return findById.get();
            }

            return new ResponseEntity<Airport>(airport, HttpStatus.OK);

        } catch (RecordNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //dodavanje aerodroma
    public ResponseEntity<?> addAirport(Airport airport){
        Optional<Airport> findById = airportRepository.findById(airport.getAirportId());

        try{
            if(!findById.isPresent()){
                airportRepository.save(airport);
                return new ResponseEntity<Airport>(airport, HttpStatus.OK);
            }
            else{
                throw new RecordAlreadyPresentException(
                        "Airport with code : " + airport.getAirportId() + " is already present");
            }
        } catch (RecordAlreadyPresentException e){
            return new ResponseEntity<Airport>(airport, HttpStatus.NOT_FOUND);
        }
    }



    public Airport modifyAirport(Airport airport){
        Optional<Airport> findById = airportRepository.findById(airport.getAirportId());

        if(findById.isPresent())
            airportRepository.save(airport);
        else
            throw new RecordNotFoundException("Airport with ID: " + airport.getAirportId() + " does not exists");

        return airport;
    }

    public String deleteAirport(String airportId){
        Optional<Airport> findById = airportRepository.findById(airportId);

        if(findById.isPresent()){
            airportRepository.deleteById(airportId);
            return "Airport removed";
        }
        else{
            throw new RecordNotFoundException("Airport with ID: " + airportId + " does not exists");
        }
    }
}
