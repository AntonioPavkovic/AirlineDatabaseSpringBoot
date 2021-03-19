package com.airlinedatabase.airlinedatabase.reposetory;

import com.airlinedatabase.airlinedatabase.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
}
