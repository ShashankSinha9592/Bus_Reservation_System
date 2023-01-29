package com.bus_reservation_system.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Size
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AdminCurrentSession {
    @Id
    private Integer adminId;
    private String key;
    private LocalDateTime localDateTime;
}
