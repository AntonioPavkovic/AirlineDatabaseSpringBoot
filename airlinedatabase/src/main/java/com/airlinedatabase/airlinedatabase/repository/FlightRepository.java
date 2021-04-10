package com.airlinedatabase.airlinedatabase.repository;

import com.airlinedatabase.airlinedatabase.model.Airport;
import com.airlinedatabase.airlinedatabase.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, BigInteger> {
    List<Flight> findAllByDepartureAirportEqualsAndDestinationAirportEqualsAndDepartureDateEquals(
            Airport depAirport, Airport destAirport, LocalDate depDate
    );
}
