package com.bus_reservation_system.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;

    @Min(value = 1,message = "Rating must not be less than 1")
    @Max(value = 10,message = "Rating must not be greater tham 10")
    private Integer driverRating;

    @Min(value = 1,message = "Rating must not be less than 1")
    @Max(value = 10,message = "Rating must not be greater tham 10")
    private Integer serviceRating;

    @Min(value = 1,message = "Rating must not be less than 1")
    @Max(value = 10,message = "Rating must not be greater tham 10")
    private Integer overAllRating;

    private String comments;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate feedbackDate;

    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @ManyToOne
    private Bus bus;

}
