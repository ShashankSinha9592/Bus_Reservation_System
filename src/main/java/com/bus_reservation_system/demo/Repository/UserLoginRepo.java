package com.bus_reservation_system.demo.Repository;

import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.Models.UserCurrentSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepo extends JpaRepository<UserCurrentSession,Integer> {

    public Optional<UserCurrentSession> findByKey(String key) throws LoginException;

}
