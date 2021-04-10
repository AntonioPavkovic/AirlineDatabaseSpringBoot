package com.airlinedatabase.airlinedatabase.repository;

import com.airlinedatabase.airlinedatabase.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface BookingRepository extends JpaRepository<Booking, BigInteger> {
}
