package com.bus_reservation_system.demo.SecurityConfiguration;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.method.HandlerMethod;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


@Configuration
public class AppConfig {

	@Bean
	public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {

		http
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/busreservation/user/registeruser","/swagger-ui*/**","/v3/api-docs/**")
				.permitAll()
				.requestMatchers("/busreservation/admin/**").hasRole("ADMIN")
				.requestMatchers("/busreservation/user/**").hasAnyRole("USER", "ADMIN")
				.anyRequest()
				.authenticated()
				.and()
				.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
				.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
				.formLogin()
				.and()
				.httpBasic();

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

}
