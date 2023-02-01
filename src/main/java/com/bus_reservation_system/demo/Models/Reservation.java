package com.bus_reservation_system.demo.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
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

    @FutureOrPresent(message = "Enter a valid date")
    private LocalDate reservationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @FutureOrPresent(message = "Enter a valid time")
    private LocalTime reservationTime;

    private String source;

    private String destination;
    @JsonIgnore
    @OneToOne
    private Bus bus;
    @JsonIgnore
    @ManyToOne
    private User user;

}
