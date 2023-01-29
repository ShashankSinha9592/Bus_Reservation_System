package com.bus_reservation_system.demo.Service.Reservation;

import com.bus_reservation_system.demo.ExceptionHandler.BusException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.ReservationException;
import com.bus_reservation_system.demo.ExceptionHandler.UserException;
import com.bus_reservation_system.demo.Models.*;
import com.bus_reservation_system.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ReservationServiceImpl implements ReservationService{

    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    UserLoginRepo userLoginRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    BusRepo busRepo;

    @Autowired
    AdminLoginRepo adminLoginRepo;


    @Override
    public Reservation addReservation(Reservation reservation, String key, Integer busId) throws LoginException, ReservationException {

        Optional<UserCurrentSession> sessionOpt = userLoginRepo.findByKey(key);

        if(sessionOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        Optional<Bus> busOpt = busRepo.findById(busId);

        if(busOpt.isEmpty()){
            throw new BusException("Bus does not exists with bus id : "+busId);
        }
        Bus bus = busOpt.get();

        if(bus.getAvailableSeats()==0){
            throw new BusException("Seats not available");
        }

        Optional<User> userOpt = userRepo.findById(sessionOpt.get().getUserId());

        User user = userOpt.get();
        bus.setAvailableSeats(bus.getAvailableSeats()-1);
        reservation.setUser(user);
        reservation.setBus(bus);
        user.getReservations().add(reservation);

        return reservationRepo.save(reservation);

    }

    @Override
    public Reservation updateReservation(Reservation reservation, String key) throws LoginException, ReservationException {

        Optional<AdminCurrentSession> adminOpt = adminLoginRepo.findByKey(key);

        if(adminOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        Optional<Reservation> reservationOpt = reservationRepo.findById(reservation.getReservationId());

        if(reservationOpt.isEmpty()){
            throw new ReservationException("Reservation does not exists with reservation id : "+reservation.getReservationId());
        }

        return reservationRepo.save(reservation);

    }

    @Override
    public Reservation deleteReservation(Integer rId, String key) throws ReservationException, LoginException {

        Optional<UserCurrentSession> sessionOpt = userLoginRepo.findByKey(key);

        if(sessionOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        Optional<User> userOpt = userRepo.findById(sessionOpt.get().getUserId());

        User user = userOpt.get();

        for(Reservation reservation:user.getReservations()){
            if(reservation.getReservationId()==rId){
                reservationRepo.delete(reservation);
                return reservation;
            }
        }
        throw new ReservationException("Reservation does not exists in user account with reservation id : "+rId);

    }

    @Override
    public Reservation viewReservationById(Integer rId, String key) throws ReservationException, LoginException {

        Optional<AdminCurrentSession> adminOpt = adminLoginRepo.findByKey(key);

        if(adminOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        Optional<Reservation> reservationOpt = reservationRepo.findById(rId);

        if(reservationOpt.isEmpty()){
            throw new ReservationException("Reservation does not exists with reservationId : "+rId);
        }

        Reservation reservation = reservationOpt.get();

        return reservation;

    }

    @Override
    public List<Reservation> viewReservationByDate(LocalDate date, String key) throws ReservationException, LoginException {

        Optional<AdminCurrentSession> adminOpt = adminLoginRepo.findByKey(key);

        if(adminOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        List<Reservation> reservations = reservationRepo.findByReservationDate(date);

        if (reservations.isEmpty()){
            throw new ReservationException("No reservations found with reservation date : "+date);
        }

        return reservations;

    }

    @Override
    public List<Reservation> viewAllReservation(String key) throws LoginException, ReservationException {

        Optional<AdminCurrentSession> adminOpt = adminLoginRepo.findByKey(key);

        if(adminOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        List<Reservation> reservations = reservationRepo.findAll();

        if(reservations.isEmpty()){
            throw new ReservationException("Reservation not found");
        }

        return reservations;

    }

    @Override
    public List<Reservation> viewAllReservationForUser(String key) throws ReservationException, LoginException {

        Optional<UserCurrentSession> sessionOpt = userLoginRepo.findByKey(key);

        if(sessionOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        Optional<User> userOpt = userRepo.findById(sessionOpt.get().getUserId());

        User user = userOpt.get();

        if(user.getReservations().isEmpty()){
            throw new ReservationException("No reservations found");
        }
        return user.getReservations();

    }
}
