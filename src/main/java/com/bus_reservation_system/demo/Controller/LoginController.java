package com.bus_reservation_system.demo.Controller;

import com.bus_reservation_system.demo.Models.User;
import com.bus_reservation_system.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class LoginController {

	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/busreservation/login")
	public ResponseEntity<User> getLoggedInCustomerDetailsHandler(Authentication auth){
		
		 User user= userRepo.findByEmail(auth.getName()).orElseThrow(() -> new BadCredentialsException("Invalid Username or password"));
		 
		 
		 return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
		
		
	}
	
}
