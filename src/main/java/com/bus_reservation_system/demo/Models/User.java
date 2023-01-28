package com.bus_reservation_system.demo.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;

    @NotNull(message = "firstname cannot be null")
    @NotBlank(message = "firstname cannot be blank")
    @NotEmpty(message = "firstname cannot be empty")
    private String firstName;

    private String lastName;

    @Email(message = "invalid email")
    @NotNull(message = "email must not be null")
    @Column(unique = true,nullable = false)
    private String email;

    @NotNull(message = "password must not be null")
    @Size(min = 6, max = 12, message = "password length must be atleast 6 and atmost 12")
    private String password;

    @Size(min = 10,max = 10 , message = "mobile number length must be 10")
    @Column(unique = true,nullable = false)
    private String mobile;

    @Past(message = "Enter a valid date of birth")
    private LocalDate dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    private Reservation reservation;

    @OneToMany
    private List<Feedback> feedbacks;
}
