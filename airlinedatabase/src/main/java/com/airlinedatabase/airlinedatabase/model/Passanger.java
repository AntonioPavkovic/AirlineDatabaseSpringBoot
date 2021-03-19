package com.airlinedatabase.airlinedatabase.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Passanger {

    @Id
    @GeneratedValue
    private long passangerId;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String passportNumber;
    private String email;
    private String address;

    @ManyToOne
    private Flight flight;

}
