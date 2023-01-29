package com.bus_reservation_system.demo.Service.Route;

import com.bus_reservation_system.demo.DTO.RouteDTO;
import com.bus_reservation_system.demo.ExceptionHandler.BusException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.RouteException;
import com.bus_reservation_system.demo.Models.AdminCurrentSession;
import com.bus_reservation_system.demo.Models.Route;
import com.bus_reservation_system.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RouteServiceImpl implements RouteService{

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

    @Autowired
    RouteRepo routeRepo;

    @Override
    public RouteDTO addRoute(Route route, String key) throws RouteException, LoginException {
        Optional<AdminCurrentSession> adminOpt = adminLoginRepo.findByKey(key);

        if(adminOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        Route savedRoute = routeRepo.save(route);

        RouteDTO routeDTO = new RouteDTO();

        routeDTO.setRouteFrom(savedRoute.getRouteFrom());
        routeDTO.setDistance(savedRoute.getDistance());
        routeDTO.setRouteTo(savedRoute.getRouteTo());

        return routeDTO;

    }

    @Override
    public Route updateRoute(Route route, String key) throws RouteException, LoginException {
        Optional<AdminCurrentSession> adminOpt = adminLoginRepo.findByKey(key);

        if(adminOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        Optional<Route> routeOpt = routeRepo.findById(route.getRouteId());

        if (routeOpt.isEmpty()){
            throw new RouteException("Route does not exists with route id : "+route.getRouteId());
        }

        Route savedRoute = routeRepo.save(route);

        return savedRoute;

    }

    @Override
    public RouteDTO viewRoute(Integer routeId, String key) throws RouteException, LoginException {
        return null;
    }

    @Override
    public Route deleteRoute(Integer routeId, String key) throws RouteException, LoginException {
        return null;
    }

    @Override
    public List<RouteDTO> viewAllRoute(String key) throws LoginException, RouteException {
        return null;
    }

    @Override
    public Route assignRouteToBus(Integer busId, Integer routeId, String key) throws LoginException, BusException, RouteException {
        return null;
    }
}
