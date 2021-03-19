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
public class Aircraft {

    @Id
    @GeneratedValue
    private long aircraftId;
    private String manufacturer;
    private String model;
    private Integer numberOfSeats;

    @OneToMany(mappedBy = "aircraft")
    private List<Flight> flights = new ArrayList<>();
}
