package com.bus_reservation_system.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(info = @Info(title = "Bus Reservation API", version = "1.0", description = "Bus Reservation Information"),
		security = {
				@SecurityRequirement(name = "basicAuth"),
				@SecurityRequirement(name = "bearerToken")
		},
		servers = {
				@Server(url = "/", description = "Bus Reservation Swagger URL")
		}
)
@SecuritySchemes({
		@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic"),
		@SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
})
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

	}

}
