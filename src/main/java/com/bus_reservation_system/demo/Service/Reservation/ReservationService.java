package com.bus_reservation_system.demo.Service.Reservation;

import com.bus_reservation_system.demo.ExceptionHandler.BusException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.ReservationException;
import com.bus_reservation_system.demo.Models.Reservation;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    public Reservation addReservation(Reservation reservation, Integer busId, Authentication authentication) throws LoginException, ReservationException, BusException;

    public Reservation updateReservation(Reservation reservation) throws LoginException, ReservationException;

    public Reservation cancelReservation(Integer rId, Authentication authentication) throws ReservationException, LoginException;

    public Reservation viewReservationById(Integer rId) throws ReservationException , LoginException;

    public List<Reservation> viewReservationByDate(LocalDate date) throws ReservationException, LoginException;

    public List<Reservation> viewAllReservation() throws LoginException, ReservationException;

    public List<Reservation> viewAllReservationForUser(Authentication authentication) throws ReservationException, LoginException;


}
