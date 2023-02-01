package com.bus_reservation_system.demo.Models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class UserCurrentSession {

    @Id
    private Integer userId;

    private String token;

    private LocalDateTime time;
}
