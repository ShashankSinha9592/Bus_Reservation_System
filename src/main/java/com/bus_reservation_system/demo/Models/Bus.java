package com.bus_reservation_system.demo.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer busId;

    @NotNull(message = "bus name must not be null")
    @NotEmpty(message = "bus name must not be empty")
    @NotBlank(message = "bus name must not be blank")
    private String busName;

    @NotNull(message = "driver must not be null")
    @Embedded
    private Driver driver;

    private String busType;

    private LocalTime arrivalTime;

    private LocalTime departureTime;

    private Integer totalSeats;

    private Integer availableSeats;

    @ManyToOne(cascade = CascadeType.ALL)
    private Route route;

    @OneToMany(mappedBy = "bus")
    private List<Feedback> feedbacks;


}
