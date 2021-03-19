package com.airlinedatabase.airlinedatabase.reposetory;

import com.airlinedatabase.airlinedatabase.model.Passanger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassangerRepository extends JpaRepository<Passanger, Long> {
}
