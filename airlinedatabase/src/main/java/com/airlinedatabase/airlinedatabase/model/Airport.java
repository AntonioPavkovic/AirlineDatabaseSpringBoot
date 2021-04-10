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
@AllArgsConstructor
@Getter
@Setter
public class Airport {

    @Id
    @GeneratedValue
    private String airportId;
    private String airportName;
    private String airportLocation;
    @OneToMany(mappedBy = "departureAirport")
    List<Flight> flights = new ArrayList<>();

    @Override
    public String toString() {
        return "Airport{" + "airportName='" + airportName + '\'' + ", airportLocation='" + airportLocation + '\''
                + ", airportId='" + airportId + '\'' + '}';
    }
}
