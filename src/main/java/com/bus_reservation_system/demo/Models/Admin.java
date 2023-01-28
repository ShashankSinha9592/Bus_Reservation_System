package com.bus_reservation_system.demo.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;

    @Column(unique = true,nullable = false)
    @Email(message = "please enter a valid email")
    @NotNull(message = "email must not be null")
    private String email;

    @NotNull(message = "password must not be null")
    @Column(nullable = false)
    @Size(min = 6 , max = 12, message = "password length must be minimum 6 and maximum 12")
    private String password;

}
