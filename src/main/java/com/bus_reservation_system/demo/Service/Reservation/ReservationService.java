package com.bus_reservation_system.demo.Service.Reservation;

import com.bus_reservation_system.demo.ExceptionHandler.BusException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.ReservationException;
import com.bus_reservation_system.demo.Models.Reservation;
import lombok.extern.java.Log;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    public Reservation addReservation(Reservation reservation, String key, Integer busId) throws LoginException, ReservationException, BusException;

    public Reservation updateReservation(Reservation reservation, String key) throws LoginException, ReservationException;

    public Reservation deleteReservation(Integer rId , String key) throws ReservationException, LoginException;

    public Reservation viewReservationById(Integer rId, String key) throws ReservationException , LoginException;

    public List<Reservation> viewReservationByDate(LocalDate date, String key) throws ReservationException, LoginException;

    public List<Reservation> viewAllReservation(String key) throws LoginException, ReservationException;

    public List<Reservation> viewAllReservationForUser(String key) throws ReservationException, LoginException;


}
