package com.bus_reservation_system.demo.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String reservationStatus;

    @NotNull(message = "reservation type must not be null")
    private String reservationType;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate reservationDate;

    @JsonIgnore
    private LocalTime reservationTime;

    private Integer numberOfSeats;

    private String source;

    private String destination;

    @JsonIgnore
    @OneToOne
    private Bus bus;

    @JsonIgnore
    @ManyToOne
    private User user;

}
