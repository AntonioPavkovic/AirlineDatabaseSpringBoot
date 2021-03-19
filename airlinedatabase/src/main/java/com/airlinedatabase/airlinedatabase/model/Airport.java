package com.airlinedatabase.airlinedatabase.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Airport {

    @Id
    @GeneratedValue
    private int airportId;
    private String airportCode;
    private String airportName;
    private String city;
    private String country;

    @OneToMany(mappedBy = "departureAirport")
    List<Flight> flights = new ArrayList<>();
}
