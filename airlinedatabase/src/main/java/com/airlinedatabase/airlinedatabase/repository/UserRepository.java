package com.airlinedatabase.airlinedatabase.repository;

import com.airlinedatabase.airlinedatabase.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, BigInteger> {
}
