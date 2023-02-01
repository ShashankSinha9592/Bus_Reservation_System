package com.bus_reservation_system.demo.Models;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    @NotNull(message = "Driver name must not be null")
    @NotEmpty(message = "Name should not be empty")
    @NotBlank(message = "Name should not be blank")
    private String driverName;

    @Size(min = 10, max = 10, message = "mobile number length must not be less than 10 nor greater than 10")
    private String mobile;


}
