package com.bus_reservation_system.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;

    @NotNull(message = "firstname cannot be null")
    @NotBlank(message = "firstname cannot be blank")
    @NotEmpty(message = "firstname cannot be empty")
    private String firstName;

    @NotNull(message = "lastname cannot be null")
    @NotBlank(message = "lastname cannot be blank")
    @NotEmpty(message = "lastname cannot be empty")
    private String lastName;

    @Email(message = "invalid email")
    @NotNull(message = "email must not be null")
    @Column(unique = true,nullable = false)
    private String email;

    @NotNull(message = "password must not be null")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Size(min = 10,max = 10 , message = "mobile number length must be 10")
    @Column(unique = true,nullable = false)
    private String mobile;

    @Past(message = "Invalid date of birth")
    @NotNull(message = "Invalid date of birth")
    private LocalDate dateOfBirth;

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",fetch=FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Authority> authorities = new HashSet<>();
}
