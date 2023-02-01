package com.bus_reservation_system.demo.Controller;

import com.bus_reservation_system.demo.DTO.BusDTO;
import com.bus_reservation_system.demo.DTO.RouteDTO;
import com.bus_reservation_system.demo.Models.*;
import com.bus_reservation_system.demo.Service.Bus.BusService;
import com.bus_reservation_system.demo.Service.Feedback.FeedbackService;
import com.bus_reservation_system.demo.Service.Reservation.ReservationService;
import com.bus_reservation_system.demo.Service.Route.RouteService;
import com.bus_reservation_system.demo.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
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

    @GetMapping("/viewbus/{busId}/{token}")
    public ResponseEntity<BusDTO> viewBusHandler(@PathVariable Integer busId, @PathVariable String token){

        BusDTO busDTO = busService.viewBus(busId,token,"user");

        return new ResponseEntity<>(busDTO,HttpStatus.OK);

    }

    @GetMapping("/viewbusbytype/{busType}/{token}")
    public ResponseEntity<List<BusDTO>> viewBusByTypeHandler(@PathVariable String busType, @PathVariable String token){

        List<BusDTO> busDTOs = busService.viewBusByType(busType,token,"user");

        return new ResponseEntity<>(busDTOs,HttpStatus.OK);

    }

    @PostMapping("/addfeedback/{busId}/{token}")
    public ResponseEntity<Feedback> registerFeedbackHandler(@Valid @RequestBody Feedback feedback , @PathVariable Integer busId , @PathVariable String token){

        Feedback savedfeedback = feedbackService.addFeedBack(feedback,token,busId);

        return new ResponseEntity<>(savedfeedback,HttpStatus.CREATED);

    }

    @PutMapping("/updatefeedback/{token}")
    public ResponseEntity<Feedback> updateFeedbackHandler(@Valid @RequestBody Feedback feedback , @PathVariable String token){

        Feedback savedfeedback = feedbackService.updateFeedback(feedback,token);

        return new ResponseEntity<>(savedfeedback,HttpStatus.ACCEPTED);

    }

    @GetMapping("/viewallfeedbackofbus/{busId}/{token}")
    public ResponseEntity<List<Feedback>> viewAllFeedbackOfBusHandler(@PathVariable Integer busId , @PathVariable String token){

        List<Feedback> feedbacks = feedbackService.viewAllFeedbacksOfBus(busId,token,"user");

        return new ResponseEntity<>(feedbacks,HttpStatus.OK);

    }


    @PostMapping("addreservation/{busId}/{token}")
    public ResponseEntity<Reservation> addReservationHandler(@Valid @RequestBody Reservation reservation , @PathVariable Integer busId, @PathVariable String token){

        Reservation savedReservation = reservationService.addReservation(reservation,token,busId);

        return new ResponseEntity<>(savedReservation,HttpStatus.CREATED);

    }

    @DeleteMapping("/cancelreservation/{reservationId}/{token}")
    public ResponseEntity<Reservation> cancelReservationHandler(@PathVariable Integer reservationId, @PathVariable String token){

        Reservation cancelledReservation = reservationService.cancelReservation(reservationId,token);

        return new ResponseEntity<>(cancelledReservation, HttpStatus.OK);

    }

    @GetMapping("/viewallreservationofuser/{token}")
    public ResponseEntity<List<Reservation>> viewAllReservationOfUserHandler(@PathVariable String token){

        List<Reservation> reservations = reservationService.viewAllReservationForUser(token);

        return new ResponseEntity<>(reservations,HttpStatus.OK);

    }


    @GetMapping("/viewallroute/{token}")
    public ResponseEntity<List<RouteDTO>> viewAllRouteHandler(@PathVariable String token){

        List<RouteDTO> routeDTOS = routeService.viewAllRoute(token,"user");

        return new ResponseEntity<>(routeDTOS,HttpStatus.OK);

    }

    @PostMapping("/registeruser")
    public ResponseEntity<User> registerUserHandler(@Valid @RequestBody User user){
        System.out.println("hellocontorller");
        User savedUser = userService.addUser(user);

        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);

    }

    @PutMapping("/updateuser/{token}")
    public ResponseEntity<User> updateUserHandler(@Valid @RequestBody User user , @PathVariable String token){

        User updatedUser = userService.updateUser(user,token);

        return new ResponseEntity<>(updatedUser,HttpStatus.ACCEPTED);

    }


}
