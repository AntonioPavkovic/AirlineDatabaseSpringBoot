package com.airlinedatabase.airlinedatabase.reposetory;

import com.airlinedatabase.airlinedatabase.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
}
