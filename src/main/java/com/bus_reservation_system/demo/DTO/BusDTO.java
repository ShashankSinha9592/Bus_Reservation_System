package com.bus_reservation_system.demo.DTO;

import com.bus_reservation_system.demo.Models.Driver;
import lombok.*;

import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class BusDTO {

    private Integer busId;

    private String busName;

    private Driver driver;

    private String busType;

    private LocalTime arrivalTime;

    private LocalTime departureTime;

    private Integer totalSeats;

    private Integer availableSeats;

    private RouteDTO routeDTO;

}
