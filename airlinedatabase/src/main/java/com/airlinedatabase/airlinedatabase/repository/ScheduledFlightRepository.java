package com.airlinedatabase.airlinedatabase.repository;

import com.airlinedatabase.airlinedatabase.model.ScheduledFlight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ScheduledFlightRepository extends JpaRepository<ScheduledFlight, BigInteger> {
}
