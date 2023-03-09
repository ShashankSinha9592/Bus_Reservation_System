package com.bus_reservation_system.demo.Service.Reservation;

import com.bus_reservation_system.demo.ExceptionHandler.BusException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.ReservationException;
import com.bus_reservation_system.demo.Models.*;
import com.bus_reservation_system.demo.Repository.*;
import com.bus_reservation_system.demo.Service.Bus.BusService;
import com.bus_reservation_system.demo.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Service
public class ReservationServiceImpl implements ReservationService{


    @Autowired
    BusService busService;

    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;



    @Override
    public Reservation addReservation(Reservation reservation, Integer busId, Authentication authentication) throws LoginException, ReservationException {

        Bus bus = busService.authenticateBus(busId);

        if(bus.getAvailableSeats()<reservation.getNumberOfSeats()){

            throw new BusException("Seats not available");

        }

        User user = userService.validateUserByEmail(authentication.getName());

        bus.setAvailableSeats(bus.getAvailableSeats()-reservation.getNumberOfSeats());

        reservation.setUser(user);

        reservation.setBus(bus);

        user.getReservations().add(reservation);

        userRepo.save(user);

        return reservation;

    }

    @Override
    public Reservation updateReservation(Reservation reservation) throws LoginException, ReservationException {

        Reservation savedReservation =  authenticateReservation(reservation.getReservationId());

        reservation.setReservationDate(LocalDate.now());

        reservation.setReservationTime(LocalTime.now());

        reservation.setUser(savedReservation.getUser());

        reservation.setBus(savedReservation.getBus());

        return reservationRepo.save(reservation);

    }

    @Override
    public Reservation cancelReservation(Integer rId, Authentication authentication) throws ReservationException, LoginException {

        User user = userService.validateUserByEmail(authentication.getName());

       Reservation reservation = authenticateReservation(rId);

       if(reservation.getUser().getUserid()==user.getUserid()){

           reservationRepo.delete(reservation);

           return reservation;
       }
       else {
           Set<Authority> authorities = user.getAuthorities();

           for(Authority authority : authorities){

               if(authority.getRole().equals("ROLE_ADMIN")){

                   reservationRepo.delete(reservation);

                   return reservation;

               }
           }
       }

        throw new ReservationException("Reservation does not exists in user account with reservation id : "+rId);

    }

    @Override
    public Reservation viewReservationById(Integer rId) throws ReservationException, LoginException {

        return authenticateReservation(rId);

    }

    @Override
    public List<Reservation> viewReservationByDate(LocalDate date) throws ReservationException, LoginException {

        List<Reservation> reservations = reservationRepo.findByReservationDate(date);

        if (reservations.isEmpty()){

            throw new ReservationException("No reservations found with reservation date : "+date);

        }

        return reservations;

    }

    @Override
    public List<Reservation> viewAllReservation() throws LoginException, ReservationException {

        List<Reservation> reservations = reservationRepo.findAll();

        if(reservations.isEmpty()){

            throw new ReservationException("Reservation not found");

        }

        return reservations;

    }

    @Override
    public List<Reservation> viewAllReservationForUser(Authentication authentication) throws ReservationException, LoginException {

        User user = userService.validateUserByEmail(authentication.getName());

        if(user.getReservations().isEmpty()){

            throw new ReservationException("No reservations found");

        }

        return user.getReservations();

    }

    private Reservation authenticateReservation(Integer rId) throws ReservationException{

        return reservationRepo.findById(rId).orElseThrow(()-> new ReservationException("reservation does not exists with reservation id : "+rId));

    }
}
