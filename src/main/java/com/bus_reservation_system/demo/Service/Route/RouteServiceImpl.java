package com.bus_reservation_system.demo.Service.Route;

import com.bus_reservation_system.demo.DTO.RouteDTO;
import com.bus_reservation_system.demo.ExceptionHandler.BusException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.RouteException;
import com.bus_reservation_system.demo.Models.AdminCurrentSession;
import com.bus_reservation_system.demo.Models.Bus;
import com.bus_reservation_system.demo.Models.Route;
import com.bus_reservation_system.demo.Models.UserCurrentSession;
import com.bus_reservation_system.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
        Optional<AdminCurrentSession> adminOpt = adminLoginRepo.findByToken(key);

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
        Optional<AdminCurrentSession> adminOpt = adminLoginRepo.findByToken(key);

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
        Optional<AdminCurrentSession> adminOpt = adminLoginRepo.findByToken(key);

        if(adminOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        Optional<Route> routeOpt = routeRepo.findById(routeId);
        if(routeOpt.isEmpty()){
            throw new RouteException("Route does exists with route id : "+routeId);
        }
        Route savedRoute = routeOpt.get();
        RouteDTO routeDTO = new RouteDTO();

        routeDTO.setRouteFrom(savedRoute.getRouteFrom());
        routeDTO.setDistance(savedRoute.getDistance());
        routeDTO.setRouteTo(savedRoute.getRouteTo());

        return routeDTO;

    }

    @Override
    public Route deleteRoute(Integer routeId, String key) throws RouteException, LoginException {

        Optional<AdminCurrentSession> adminOpt = adminLoginRepo.findByToken(key);

        if(adminOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        Optional<Route> routeOpt = routeRepo.findById(routeId);

        if (routeOpt.isEmpty()){
            throw new RouteException("Route does not exists with route id : "+routeId);
        }

        Route route = routeOpt.get();

        routeRepo.delete(route);
        return route;


    }

    @Override
    public List<RouteDTO> viewAllRoute(String key, String check) throws LoginException, RouteException {

        if(check.equals("admin")) {
            Optional<AdminCurrentSession> adminOpt = adminLoginRepo.findByToken(key);

            if (adminOpt.isEmpty()) {
                throw new LoginException("Please login first");
            }
        }
        if(check.equals("user")){
            Optional<UserCurrentSession> userOpt = userLoginRepo.findByToken(key);

            if (userOpt.isEmpty()) {
                throw new LoginException("Please login first");
            }
        }

        List<Route> routes = routeRepo.findAll();

        if(routes.isEmpty()){
            throw new RouteException("No route found");
        }

        List<RouteDTO> routeDtos = new ArrayList<>();

        for(Route route : routes){
            RouteDTO dto = new RouteDTO();
            dto.setDistance(route.getDistance());
            dto.setRouteTo(route.getRouteTo());
            dto.setRouteFrom(route.getRouteFrom());
            routeDtos.add(dto);
        }

        return routeDtos;

    }

    @Override
    public Route assignRouteToBus(Integer busId, Integer routeId, String key) throws LoginException, BusException, RouteException {

        Optional<AdminCurrentSession> adminOpt = adminLoginRepo.findByToken(key);

        if(adminOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        Optional<Bus> busOpt = busRepo.findById(busId);

        if(busOpt.isEmpty()){
            throw new BusException("Bus does not exists with bus id : "+busId);
        }

        Optional<Route> routeOpt = routeRepo.findById(routeId);

        if(routeOpt.isEmpty()){
            throw new RouteException("Route does not exists with route id : "+routeId);
        }

        Bus bus = busOpt.get();
        Route route = routeOpt.get();

        route.getBuses().add(bus);
        bus.setRoute(route);
        busRepo.save(bus);
        routeRepo.save(route);

        return route;

    }
}
