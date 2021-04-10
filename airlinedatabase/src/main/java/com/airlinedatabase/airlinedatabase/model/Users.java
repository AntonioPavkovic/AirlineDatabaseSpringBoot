package com.airlinedatabase.airlinedatabase.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger userId;

    @Column(nullable=false, unique = true)
    @NotBlank(message = "Username is required")
    private String username;

    @Column(nullable=false, unique = true)
    @NotBlank(message = "Email is required")
    @Email(message="{errors.invalid_email}")
    private String email;

    @Column(nullable=false)
    @NotBlank(message = "Password is required")
    @Size(min=8)
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "Phone is required")
    @Size(min = 10)
    private String phoneNumber;

}
