package com.bus_reservation_system.demo.Repository;

import com.bus_reservation_system.demo.ExceptionHandler.RouteException;
import com.bus_reservation_system.demo.ExceptionHandler.UserException;
import com.bus_reservation_system.demo.Models.Bus;
import com.bus_reservation_system.demo.Models.Route;
import com.bus_reservation_system.demo.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    public Optional<User> findByEmail(String email) throws UserException;

}
