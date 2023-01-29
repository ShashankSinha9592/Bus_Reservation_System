package com.bus_reservation_system.demo.DTO;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class RouteDTO {
    private String routeFrom;

    private String routeTo;

    private Integer distance;
}
