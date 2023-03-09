package com.bus_reservation_system.demo.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @JsonFormat(pattern = "HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalTime arrivalTime;

    @JsonFormat(pattern = "HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalTime departureTime;

    @Min(value = 20, message = "bus cannot have seats less than 20")
    private Integer totalSeats;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer availableSeats;

    @JsonIgnore
    @ManyToOne
    private Route route;

    @JsonIgnore
    @OneToMany(mappedBy = "bus")
    private List<Feedback> feedbacks= new ArrayList<>();

}
