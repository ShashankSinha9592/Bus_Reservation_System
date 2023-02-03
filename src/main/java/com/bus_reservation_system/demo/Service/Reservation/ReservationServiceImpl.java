package com.bus_reservation_system.demo.Service.Reservation;

import com.bus_reservation_system.demo.ExceptionHandler.BusException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.ReservationException;
import com.bus_reservation_system.demo.Models.*;
import com.bus_reservation_system.demo.Repository.*;
import com.bus_reservation_system.demo.Service.Bus.BusService;
import com.bus_reservation_system.demo.Service.LoginService.Admin.AdminAuthentication;
import com.bus_reservation_system.demo.Service.LoginService.User.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    UserAuthentication userAuthentication;

    @Autowired
    BusService busService;

    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    AdminAuthentication adminAuthentication;


    @Override
    public Reservation addReservation(Reservation reservation, String token, Integer busId) throws LoginException, ReservationException {

        UserCurrentSession userCurrentSession = userAuthentication.authenticateUserLoginSession(token);

        Bus bus = busService.authenticateBus(busId);

        if(bus.getAvailableSeats()==0){
            throw new BusException("Seats not available");
        }

        User user = userAuthentication.authenticateUser(userCurrentSession.getUserId());

        bus.setAvailableSeats(bus.getAvailableSeats()-1);
        reservation.setUser(user);
        reservation.setBus(bus);
        user.getReservations().add(reservation);

        userRepo.save(user);

        return reservation;

    }

    @Override
    public Reservation updateReservation(Reservation reservation, String token) throws LoginException, ReservationException {

        adminAuthentication.authenticateAdminLoginSession(token);

        authenticateReservation(reservation.getReservationId());

        return reservationRepo.save(reservation);

    }

    @Override
    public Reservation cancelReservation(Integer rId, String token) throws ReservationException, LoginException {

        UserCurrentSession userCurrentSession = userAuthentication.authenticateUserLoginSession(token);

        User user = userAuthentication.authenticateUser(userCurrentSession.getUserId());

        for(Reservation reservation:user.getReservations()){
            if(reservation.getReservationId()==rId){
                reservationRepo.delete(reservation);
                return reservation;
            }
        }
        throw new ReservationException("Reservation does not exists in user account with reservation id : "+rId);

    }

    @Override
    public Reservation viewReservationById(Integer rId, String token) throws ReservationException, LoginException {

        adminAuthentication.authenticateAdminLoginSession(token);

        return authenticateReservation(rId);

    }

    @Override
    public List<Reservation> viewReservationByDate(LocalDate date, String token) throws ReservationException, LoginException {

        adminAuthentication.authenticateAdminLoginSession(token);

        List<Reservation> reservations = reservationRepo.findByReservationDate(date);

        if (reservations.isEmpty()){
            throw new ReservationException("No reservations found with reservation date : "+date);
        }

        return reservations;

    }

    @Override
    public List<Reservation> viewAllReservation(String token) throws LoginException, ReservationException {

        adminAuthentication.authenticateAdminLoginSession(token);

        List<Reservation> reservations = reservationRepo.findAll();

        if(reservations.isEmpty()){
            throw new ReservationException("Reservation not found");
        }

        return reservations;

    }

    @Override
    public List<Reservation> viewAllReservationForUser(String token) throws ReservationException, LoginException {

        UserCurrentSession userCurrentSession = userAuthentication.authenticateUserLoginSession(token);

        User user = userAuthentication.authenticateUser(userCurrentSession.getUserId());

        if(user.getReservations().isEmpty()){
            throw new ReservationException("No reservations found");
        }
        return user.getReservations();

    }

    private Reservation authenticateReservation(Integer rId) throws ReservationException{

        Optional<Reservation> reservationOpt = reservationRepo.findById(rId);

        if(reservationOpt.isEmpty()){
            throw new ReservationException("Reservation does not exists with reservationId : "+rId);
        }

        return reservationOpt.get();

    }
}
