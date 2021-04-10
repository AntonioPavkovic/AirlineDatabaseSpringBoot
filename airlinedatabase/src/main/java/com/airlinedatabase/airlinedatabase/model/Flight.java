package com.airlinedatabase.airlinedatabase.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue
    private BigInteger flightId;
    private int seatCapacity;
    private String carrierName;
    private String flightModel;

    @ManyToOne
    private Airport departureAirport;

    @ManyToOne
    private Airport destinationAirport;

    @OneToMany(mappedBy = "flight")
    List<Passenger> passengers = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;

    private String departureTime;
    private String arrivalTime;
    private double flightCharge;
}
