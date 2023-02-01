package com.bus_reservation_system.demo.Controller;


import com.bus_reservation_system.demo.DTO.BusDTO;
import com.bus_reservation_system.demo.DTO.RouteDTO;
import com.bus_reservation_system.demo.ExceptionHandler.AdminException;
import com.bus_reservation_system.demo.Models.*;
import com.bus_reservation_system.demo.Service.AdminService.AdminService;
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
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    BusService busService;

    @Autowired
    AdminService adminService;

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    RouteService routeService;

    @Autowired
    UserService userService;

    @PostMapping("/registeradmin/{requestToken}")
    public ResponseEntity<Admin> registerAdminHandler(@Valid @RequestBody Admin admin, @PathVariable String requestToken){

        String token = "abcd1234";

        Admin savedAdmin=null;

        if(adminService.checkToken(token,requestToken)){

            savedAdmin =  adminService.registerAdmin(admin);

        }
        System.out.println("hello admin contoller");
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);

    }

    @PostMapping("/registerbus/{token}")
    public ResponseEntity<Bus> registerBusHandler(@Valid @RequestBody Bus bus, @PathVariable String token){

        Bus savedBus = busService.addBus(bus,token);

        return new ResponseEntity<>(savedBus,HttpStatus.CREATED);

    }

    @PutMapping("/updatebus/{token}")
    public ResponseEntity<Bus> updateBusHandler(@Valid @RequestBody Bus bus, @PathVariable String token){

        Bus savedBus = busService.updateBus(bus,token);

        return new ResponseEntity<>(savedBus,HttpStatus.ACCEPTED);

    }

    @GetMapping("/viewbus/{busId}/{token}")
    public ResponseEntity<BusDTO> viewBusHandler(@PathVariable Integer busId,  @PathVariable String token){

        BusDTO savedBus = busService.viewBus(busId,token,"admin");

        return new ResponseEntity<>(savedBus,HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/deletebus/{busId}/{token}")
    public ResponseEntity<Bus> deleteBusHandler(@PathVariable Integer busId,  @PathVariable String token){

        Bus savedBus = busService.deleteBus(busId,token);

        return new ResponseEntity<>(savedBus,HttpStatus.ACCEPTED);

    }

    @GetMapping("/viewallbus/{token}")
    public ResponseEntity<List<BusDTO>> viewAllBusHandler(@PathVariable String token){

        List<BusDTO> savedBus = busService.viewAllBus(token);

        return new ResponseEntity<>(savedBus,HttpStatus.ACCEPTED);

    }

    @GetMapping("/viewbusbytype/{busType}/{token}")
    public ResponseEntity<List<BusDTO>> viewAllBusByTypeHandler(@PathVariable String busType , @PathVariable String token){

        List<BusDTO> savedBus = busService.viewBusByType(busType,token,"admin");

        return new ResponseEntity<>(savedBus,HttpStatus.ACCEPTED);

    }

    @GetMapping("/viewfeedback/{feedbackId}/{token}")
    public ResponseEntity<Feedback> viewFeedbackByIdHandler(@PathVariable Integer feedbackId, @PathVariable String token){

        Feedback feedback = feedbackService.viewFeedbackById(feedbackId,token);

        return new ResponseEntity<>(feedback,HttpStatus.OK);

    }


    @GetMapping("/viewallfeedback/{token}")
    public ResponseEntity<List<Feedback>> viewAllFeedbackByIdHandler( @PathVariable String token){

        List<Feedback> feedbacks = feedbackService.viewAllFeedback(token);

        return new ResponseEntity<>(feedbacks,HttpStatus.OK);

    }

    @GetMapping("/viewallfeedbackofbus/{busId}/{token}")
    public ResponseEntity<List<Feedback>> viewAllFeedbackByIdHandler(@PathVariable Integer busId, @PathVariable String token){

        List<Feedback> feedbacks = feedbackService.viewAllFeedbacksOfBus(busId,token,"admin");

        return new ResponseEntity<>(feedbacks,HttpStatus.OK);

    }


    @PutMapping("/updatereservation/{token}")
    public ResponseEntity<Reservation> updateReservationHandler(@Valid @RequestBody Reservation reservation , @PathVariable String token){

        Reservation updatedReservation = reservationService.updateReservation(reservation,token);

        return new ResponseEntity<>(updatedReservation,HttpStatus.CREATED);

    }


    @GetMapping("/viewreservation/{reservationId}/{token}")
    public ResponseEntity<Reservation> viewReservationHandler(@PathVariable Integer reservationId , @PathVariable String token){

        Reservation reservation = reservationService.viewReservationById(reservationId,token);

        return new ResponseEntity<>(reservation,HttpStatus.OK);

    }

    @GetMapping("/viewreservationbydate/{date}/{token}")
    public ResponseEntity<List<Reservation>> viewReservationByDateHandler(@PathVariable String date , @PathVariable String token){

        LocalDate localDate = LocalDate.parse(date);

        List<Reservation> reservations = reservationService.viewReservationByDate(localDate,token);

        return new ResponseEntity<>(reservations,HttpStatus.OK);

    }

    @GetMapping("/viewallreservation/{token}")
    public ResponseEntity<List<Reservation>> viewallReservationHandler(@PathVariable String token){

        List<Reservation> reservations = reservationService.viewAllReservation(token);

        return new ResponseEntity<>(reservations,HttpStatus.OK);

    }

    @PostMapping("/addroute/{token}")
    public ResponseEntity<RouteDTO> registerRouteHandler(@Valid @RequestBody Route route , @PathVariable String token){

        RouteDTO routeDTO = routeService.addRoute(route,token);

        return new ResponseEntity<>(routeDTO,HttpStatus.CREATED);

    }

    @PutMapping("/updateroute/{token}")
    public ResponseEntity<Route> updateRouteHandler(@Valid @RequestBody Route route , @PathVariable String token){

        Route updateRoute = routeService.updateRoute(route,token);

        return new ResponseEntity<>(updateRoute,HttpStatus.CREATED);

    }

    @GetMapping("/viewroute/{routeId}/{token}")
    public ResponseEntity<RouteDTO> viewRouteHandler(@PathVariable Integer routeId , @PathVariable String token){

        RouteDTO routeDTO = routeService.viewRoute(routeId,token);

        return new ResponseEntity<>(routeDTO,HttpStatus.OK);

    }

    @DeleteMapping("/deleteroute/{routeId}/{token}")
    public ResponseEntity<Route> deleteRouteHandler(@PathVariable Integer routeId , @PathVariable String token){

        Route removedRoute = routeService.deleteRoute(routeId,token);

        return new ResponseEntity<>(removedRoute,HttpStatus.OK);

    }

    @GetMapping("/viewallroute/{routeId}/{token}")
    public ResponseEntity<List<RouteDTO>> viewAllRouteHandler( @PathVariable String token){

        List<RouteDTO> routeDTOs = routeService.viewAllRoute(token,"admin");

        return new ResponseEntity<>(routeDTOs,HttpStatus.OK);

    }

    @PatchMapping("/assignroutetobus/{busId}/{routeId}/{token}")
    public ResponseEntity<Route> assignRouteToBusHandler(@PathVariable Integer busId, @PathVariable Integer routeId, @PathVariable String token){

        Route route = routeService.assignRouteToBus(busId,routeId,token);

        return new ResponseEntity<>(route, HttpStatus.OK);

    }

    @GetMapping("/viewuser/{userId}/{token}")
    public ResponseEntity<User> viewUserHandler(@PathVariable Integer userId, @PathVariable String token){

        User user = userService.viewUser(userId, token, "admin");

        return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @DeleteMapping("/deleteuser/{userId}/{token}")
    public ResponseEntity<User> deleteUserHandler(@PathVariable Integer userId, @PathVariable String token){

        User user = userService.deleteUser(userId, token);

        return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @GetMapping("/viewalluser/{token}")
    public ResponseEntity<List<User>> viewAllUserHandler(@PathVariable String token){

        List<User> users = userService.viewAllUser(token);

        return new ResponseEntity<>(users , HttpStatus.OK);

    }




}
