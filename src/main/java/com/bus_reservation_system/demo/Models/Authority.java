package com.bus_reservation_system.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Authority {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer authId;

	@NotNull(message = "role cannot be null")
	@NotBlank(message = "role cannot be blank")
	@NotEmpty(message = "role cannot be empty")
	private String role;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	
}
