package com.airlinedatabase.airlinedatabase.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue
    private long flightId;
    private String flightNumber;

    @ManyToOne
    private Airport departureAirport;

    @ManyToOne
    private Airport destinationAirport;

    @ManyToOne
    private Aircraft aircraft;

    @OneToMany(mappedBy = "flight")
    List<Passanger> passengers = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;

    private String departureTime;
    private String arrivalTime;
    private double flightCharge;
}
