package com.bus_reservation_system.demo.Models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class AdminCurrentSession {
    @Id
    private Integer adminId;
    private String token;
    private LocalDateTime time;
}
