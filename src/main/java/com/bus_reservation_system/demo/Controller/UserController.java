package com.bus_reservation_system.demo.Controller;

import com.bus_reservation_system.demo.DTO.BusDTO;
import com.bus_reservation_system.demo.DTO.RouteDTO;
import com.bus_reservation_system.demo.ExceptionHandler.UserException;
import com.bus_reservation_system.demo.Models.*;
import com.bus_reservation_system.demo.Service.Bus.BusService;
import com.bus_reservation_system.demo.Service.Feedback.FeedbackService;
import com.bus_reservation_system.demo.Service.Reservation.ReservationService;
import com.bus_reservation_system.demo.Service.Route.RouteService;
import com.bus_reservation_system.demo.Service.User.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("busreservation/user")
public class UserController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    BusService busService;

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    RouteService routeService;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/viewbus/{busId}")
    public ResponseEntity<BusDTO> viewBusHandler(@PathVariable Integer busId){

        BusDTO busDTO = busService.viewBus(busId);

        return new ResponseEntity<>(busDTO,HttpStatus.OK);

    }

    @GetMapping("/viewbusbytype/{busType}")
    public ResponseEntity<List<BusDTO>> viewAllBusByTypeHandler(@PathVariable String busType){

        List<BusDTO> savedBus = busService.viewBusByType(busType);

        return new ResponseEntity<>(savedBus,HttpStatus.ACCEPTED);

    }

    @PostMapping("/addfeedback/{busId}")
    public ResponseEntity<Feedback> registerFeedbackHandler(@Valid @RequestBody Feedback feedback , @PathVariable Integer busId , Authentication authentication){

        feedback.setFeedbackDate(LocalDate.now());

        Feedback savedfeedback = feedbackService.addFeedBack(feedback,busId,authentication);

        return new ResponseEntity<>(savedfeedback,HttpStatus.CREATED);

    }

    @PutMapping("/updatefeedback")
    public ResponseEntity<Feedback> updateFeedbackHandler(@Valid @RequestBody Feedback feedback, Authentication authentication){

        Feedback savedfeedback = feedbackService.updateFeedback(feedback, authentication);

        return new ResponseEntity<>(savedfeedback,HttpStatus.ACCEPTED);

    }

    @GetMapping("/viewallfeedbackofbus/{busId}")
    public ResponseEntity<List<Feedback>> viewAllFeedbackByBusIdHandler(@PathVariable Integer busId){

        List<Feedback> feedbacks = feedbackService.viewAllFeedbacksOfBus(busId);

        return new ResponseEntity<>(feedbacks,HttpStatus.OK);

    }


    @PostMapping("addreservation/{busId}")
    public ResponseEntity<Reservation> addReservationHandler(@Valid @RequestBody Reservation reservation , @PathVariable Integer busId, Authentication authentication){

        reservation.setReservationTime(LocalTime.now());

        reservation.setReservationStatus("Completed");

        reservation.setReservationDate(LocalDate.now());

        Reservation savedReservation = reservationService.addReservation(reservation,busId,authentication);

        return new ResponseEntity<>(savedReservation,HttpStatus.CREATED);

    }

    @DeleteMapping("/cancelreservation/{reservationId}")
    public ResponseEntity<Reservation> cancelReservationHandler(@PathVariable Integer reservationId, Authentication authentication){

        Reservation cancelledReservation = reservationService.cancelReservation(reservationId,authentication);

        return new ResponseEntity<>(cancelledReservation, HttpStatus.OK);

    }

    @GetMapping("/viewallreservationofuser")
    public ResponseEntity<List<Reservation>> viewAllReservationOfUserHandler(Authentication authentication){

        List<Reservation> reservations = reservationService.viewAllReservationForUser(authentication);

        return new ResponseEntity<>(reservations,HttpStatus.OK);

    }


    @GetMapping("/viewallroute")
    public ResponseEntity<List<RouteDTO>> viewAllRouteHandler(){

        List<RouteDTO> routeDTOS = routeService.viewAllRoute();

        return new ResponseEntity<>(routeDTOS,HttpStatus.OK);

    }

    @PostMapping("/registeruser")
    public ResponseEntity<User> registerUserHandler(@Valid @RequestBody User user){

        for(Authority auth : user.getAuthorities()){
            auth.setRole("ROLE_"+auth.getRole().toUpperCase());

            if(!auth.getRole().equals("ROLE_USER") && !auth.getRole().equals("ROLE_ADMIN")){
                throw new UserException("authority role must be user or admin");
            }

        }

        User savedUser = userService.addUser(user);

        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);

    }

    @PutMapping("/updateuser")
    public ResponseEntity<User> updateUserHandler(@Valid @RequestBody User user, Authentication authentication){

        User updatedUser = userService.updateUser(user, authentication);

        return new ResponseEntity<>(updatedUser,HttpStatus.ACCEPTED);

    }

    @GetMapping("/viewbusbyroute/{startRoute}/{endRoute}")
    public ResponseEntity<List<BusDTO>> getBusByRoute(@PathVariable String startRoute, @PathVariable String endRoute){

        List<BusDTO> buses = busService.viewBusesByRoute(startRoute,endRoute);

        return new ResponseEntity<>(buses,HttpStatus.OK);

    }
    @DeleteMapping("/deleteuser/{userId}")
    public ResponseEntity<User> deleteUserHandler(@PathVariable Integer userId, Authentication authentication){

        User user = userService.deleteUser(userId, authentication);

        return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @GetMapping("/viewuser/{userId}")
    public ResponseEntity<User> viewUserHandler(@PathVariable Integer userId, Authentication authentication){

        User user = userService.viewUser(userId, authentication);

        return new ResponseEntity<>(user,HttpStatus.OK);

    }


}
