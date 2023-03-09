package com.bus_reservation_system.demo.Controller;


import com.bus_reservation_system.demo.DTO.BusDTO;
import com.bus_reservation_system.demo.DTO.RouteDTO;
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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("busreservation/admin")
public class AdminController {

    @Autowired
    BusService busService;

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    RouteService routeService;

    @Autowired
    UserService userService;


    @PostMapping("/registerbus")
    public ResponseEntity<Bus> registerBusHandler(@Valid @RequestBody Bus bus){

        bus.setAvailableSeats(bus.getTotalSeats());

        Bus savedBus = busService.addBus(bus);

        return new ResponseEntity<>(savedBus,HttpStatus.CREATED);

    }

    @PutMapping("/updatebus")
    public ResponseEntity<Bus> updateBusHandler(@Valid @RequestBody Bus bus){

        Bus savedBus = busService.updateBus(bus);

        return new ResponseEntity<>(savedBus,HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/deletebus/{busId}")
    public ResponseEntity<Bus> deleteBusHandler(@PathVariable Integer busId){

        Bus savedBus = busService.deleteBus(busId);

        return new ResponseEntity<>(savedBus,HttpStatus.ACCEPTED);

    }

    @GetMapping("/viewallbus")
    public ResponseEntity<List<BusDTO>> viewAllBusHandler(){

        List<BusDTO> savedBus = busService.viewAllBus();

        return new ResponseEntity<>(savedBus,HttpStatus.ACCEPTED);

    }

    @GetMapping("/viewfeedback/{feedbackId}")
    public ResponseEntity<Feedback> viewFeedbackByIdHandler(@PathVariable Integer feedbackId){

        Feedback feedback = feedbackService.viewFeedbackById(feedbackId);

        return new ResponseEntity<>(feedback,HttpStatus.OK);

    }

    @GetMapping("/viewallfeedback")
    public ResponseEntity<List<Feedback>> viewAllFeedbackHandler(){

        List<Feedback> feedbacks = feedbackService.viewAllFeedback();

        return new ResponseEntity<>(feedbacks,HttpStatus.OK);

    }

    @PutMapping("/updatereservation")
    public ResponseEntity<Reservation> updateReservationHandler(@Valid @RequestBody Reservation reservation){

        Reservation updatedReservation = reservationService.updateReservation(reservation);

        return new ResponseEntity<>(updatedReservation,HttpStatus.CREATED);

    }


    @GetMapping("/viewreservation/{reservationId}")
    public ResponseEntity<Reservation> viewReservationHandler(@PathVariable Integer reservationId){

        Reservation reservation = reservationService.viewReservationById(reservationId);

        return new ResponseEntity<>(reservation,HttpStatus.OK);

    }

    @GetMapping("/viewreservationbydate/{date}")
    public ResponseEntity<List<Reservation>> viewReservationByDateHandler(@PathVariable LocalDate date){


        List<Reservation> reservations = reservationService.viewReservationByDate(date);

        return new ResponseEntity<>(reservations,HttpStatus.OK);

    }

    @GetMapping("/viewallreservation")
    public ResponseEntity<List<Reservation>> viewallReservationHandler(){

        List<Reservation> reservations = reservationService.viewAllReservation();

        return new ResponseEntity<>(reservations,HttpStatus.OK);

    }

    @PostMapping("/addroute")
    public ResponseEntity<RouteDTO> registerRouteHandler(@Valid @RequestBody Route route){

        RouteDTO routeDTO = routeService.addRoute(route);

        return new ResponseEntity<>(routeDTO,HttpStatus.CREATED);

    }

    @PutMapping("/updateroute")
    public ResponseEntity<Route> updateRouteHandler(@Valid @RequestBody Route route ){

        Route updateRoute = routeService.updateRoute(route);

        return new ResponseEntity<>(updateRoute,HttpStatus.CREATED);

    }

    @GetMapping("/viewroute/{routeId}")
    public ResponseEntity<RouteDTO> viewRouteHandler(@PathVariable Integer routeId){

        RouteDTO routeDTO = routeService.viewRoute(routeId);

        return new ResponseEntity<>(routeDTO,HttpStatus.OK);

    }

    @DeleteMapping("/deleteroute/{routeId}")
    public ResponseEntity<Route> deleteRouteHandler(@PathVariable Integer routeId){

        Route removedRoute = routeService.deleteRoute(routeId);

        return new ResponseEntity<>(removedRoute,HttpStatus.OK);

    }

    @GetMapping("/viewallroute")
    public ResponseEntity<List<RouteDTO>> viewAllRouteHandler(){

        List<RouteDTO> routeDTOs = routeService.viewAllRoute();

        return new ResponseEntity<>(routeDTOs,HttpStatus.OK);

    }

    @PatchMapping("/assignroutetobus/{busId}/{routeId}")
    public ResponseEntity<Route> assignRouteToBusHandler(@PathVariable Integer busId, @PathVariable Integer routeId){

        Route route = routeService.assignRouteToBus(busId,routeId);

        return new ResponseEntity<>(route, HttpStatus.OK);

    }

    @GetMapping("/viewalluser")
    public ResponseEntity<List<User>> viewAllUserHandler(){

        List<User> users = userService.viewAllUser();

        return new ResponseEntity<>(users , HttpStatus.OK);

    }


}
