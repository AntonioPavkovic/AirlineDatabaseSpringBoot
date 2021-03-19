package com.airlinedatabase.airlinedatabase.services;

import com.airlinedatabase.airlinedatabase.model.Airport;
import com.airlinedatabase.airlinedatabase.model.Flight;
import com.airlinedatabase.airlinedatabase.reposetory.FlightRepository;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    public abstract Page<Flight> getAllFlightsPaged(int pageNum);
    public abstract List<Flight> getAllFlights();
    public abstract Flight getFlightById(long flightId);
    public abstract Flight saveFlight(Flight flight);
    public abstract void deleteFlightById(long flightId);
    public abstract List<Flight> getAllFlightsByAirportAndDepartureTime(Airport depAirport, Airport destAirport, LocalDate depDate);
}
