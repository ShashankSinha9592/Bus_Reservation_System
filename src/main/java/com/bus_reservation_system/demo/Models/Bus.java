package com.bus_reservation_system.demo.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime arrivalTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime departureTime;

    private Integer totalSeats;

    private Integer availableSeats;

    @JsonIgnore
    @ManyToOne
    private Route route;

    @JsonIgnore
    @OneToMany(mappedBy = "bus")
    private List<Feedback> feedbacks;


}
