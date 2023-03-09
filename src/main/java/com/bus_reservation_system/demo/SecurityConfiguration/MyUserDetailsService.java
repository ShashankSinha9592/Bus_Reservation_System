package com.bus_reservation_system.demo.SecurityConfiguration;

import com.bus_reservation_system.demo.Models.Authority;
import com.bus_reservation_system.demo.Models.User;
import com.bus_reservation_system.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> opt = userRepo.findByEmail(username);

		System.out.println(username + " " + opt.get().getEmail()+" "+ opt.isPresent());

		if (opt.isPresent()) {
			System.out.println("load user end 1");
			User user = opt.get();

			List<GrantedAuthority> authorities = new ArrayList<>();

			Set<Authority> auths = user.getAuthorities();

			System.out.println("load user end 2");

			for (Authority auth : auths) {
				System.out.println("load user end loop "+ auth.getRole() );
				SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(auth.getRole());
				authorities.add(simpleGrantedAuthority);
			}
			System.out.println("load user end 3");
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

		} else {

			System.out.println("load user throws exception");
			throw new BadCredentialsException("User Details not found with this username: " + username);
			}
	}
	
	
//	 private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
//	        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//	        for (Authority authority : authorities) {
//	            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
//	        }
//	        return grantedAuthorities;
//	    }

}
