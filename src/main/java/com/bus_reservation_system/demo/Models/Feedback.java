package com.bus_reservation_system.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
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

    private LocalDate feedbackDate;
    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @ManyToOne
    private Bus bus;

}
