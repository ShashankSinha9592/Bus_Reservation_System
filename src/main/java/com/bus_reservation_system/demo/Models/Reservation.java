package com.bus_reservation_system.demo.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @NotNull(message = "status must not be null")
    private String reservationStatus;

    @NotNull(message = "reservation type must not be null")
    private String reservationType;

    @Future(message = "Enter a valid date")
    private LocalDate reservationDate;

    @Future(message = "Enter a valid time")
    private LocalTime reservationTime;

    private String source;

    private String destination;

    @OneToOne(cascade = CascadeType.ALL)
    private Bus bus;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

}
