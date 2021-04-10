package com.airlinedatabase.airlinedatabase.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Passenger {

    @Id
    @GeneratedValue
    private long passengerId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private BigInteger phoneNumber;
    @NotBlank
    private String passportNumber;
    private String email;
    private String address;
    private Double luggage;

}
