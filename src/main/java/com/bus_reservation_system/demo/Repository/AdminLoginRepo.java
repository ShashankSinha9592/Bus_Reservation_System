package com.bus_reservation_system.demo.Repository;

import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.Models.Admin;
import com.bus_reservation_system.demo.Models.AdminCurrentSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminLoginRepo extends JpaRepository<AdminCurrentSession,Integer> {

    public Optional<AdminCurrentSession> findByKey(String key) throws LoginException;

}
