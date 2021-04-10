package com.airlinedatabase.airlinedatabase.repository;

import com.airlinedatabase.airlinedatabase.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ScheduleRepository extends JpaRepository<Schedule, BigInteger> {
}
