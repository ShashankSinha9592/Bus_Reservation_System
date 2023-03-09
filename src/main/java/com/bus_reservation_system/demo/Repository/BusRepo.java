package com.bus_reservation_system.demo.Repository;

import com.bus_reservation_system.demo.ExceptionHandler.BusException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.Models.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepo extends JpaRepository<Bus,Integer> {
    
    public List<Bus> findByBusType(String busType) throws BusException, LoginException;
}
