package com.bus_reservation_system.demo.Models;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Login {
    @NotNull(message = "email must not be null")
    @Email(message = "Invalid email")
    private String email;

    @Size(min = 6, max = 12 , message = "password length must be atleast 6 and atmost 12")
    private String password;
}
