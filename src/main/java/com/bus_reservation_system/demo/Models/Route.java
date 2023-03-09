package com.bus_reservation_system.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer routeId;

    @NotNull(message = "starting route cannot be null")
    private String routeFrom;

    @NotNull(message = "Ending route cannot be null")
    private String routeTo;

    @NotNull
    @Min(value = 100, message = "distance must be atleast 100")
    @Max(value = 10000,message = "distance must be atmost 10000")
    private Integer distance;

    @JsonIgnore
    @OneToMany(mappedBy = "route")
    private List<Bus> buses = new ArrayList<>();

}
