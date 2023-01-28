package com.bus_reservation_system.demo.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
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

    @OneToMany
    private List<Bus> buses;

}
