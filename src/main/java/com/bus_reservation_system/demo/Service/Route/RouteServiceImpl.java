package com.bus_reservation_system.demo.Service.Route;

import com.bus_reservation_system.demo.DTO.RouteDTO;
import com.bus_reservation_system.demo.ExceptionHandler.BusException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.RouteException;
import com.bus_reservation_system.demo.Models.Bus;
import com.bus_reservation_system.demo.Models.Route;
import com.bus_reservation_system.demo.Repository.*;
import com.bus_reservation_system.demo.Service.Bus.BusService;
import com.bus_reservation_system.demo.Service.LoginService.Admin.AdminAuthentication;
import com.bus_reservation_system.demo.Service.LoginService.User.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService{

    @Autowired
    BusService busService;

    @Autowired
    UserAuthentication userAuthentication;

    @Autowired
    AdminAuthentication adminAuthentication;

    @Autowired
    BusRepo busRepo;

    @Autowired
    RouteRepo routeRepo;

    @Override
    public RouteDTO addRoute(Route route, String token) throws RouteException, LoginException {
        adminAuthentication.authenticateAdminLoginSession(token);

        Route savedRoute = routeRepo.save(route);

        return getRouteDto(savedRoute);

    }

    @Override
    public Route updateRoute(Route route, String token) throws RouteException, LoginException {
        adminAuthentication.authenticateAdminLoginSession(token);

        authenticateRoute(route.getRouteId());

        return routeRepo.save(route);

    }

    @Override
    public RouteDTO viewRoute(Integer routeId, String token) throws RouteException, LoginException {
        adminAuthentication.authenticateAdminLoginSession(token);

        Route savedRoute = authenticateRoute(routeId);

        return getRouteDto(savedRoute);

    }

    @Override
    public Route deleteRoute(Integer routeId, String token) throws RouteException, LoginException {

        adminAuthentication.authenticateAdminLoginSession(token);

        Route route = authenticateRoute(routeId);

        routeRepo.delete(route);

        return route;


    }

    @Override
    public List<RouteDTO> viewAllRoute(String token, String check) throws LoginException, RouteException {

        if(check.equals("admin")) {
            adminAuthentication.authenticateAdminLoginSession(token);
        }
        if(check.equals("user")){
            userAuthentication.authenticateUserLoginSession(token);
        }

        List<Route> routes = routeRepo.findAll();

        if(routes.isEmpty()){
            throw new RouteException("No route found");
        }

        List<RouteDTO> routeDtos = new ArrayList<>();

        for(Route route : routes){
            RouteDTO dto = getRouteDto(route);

            routeDtos.add(dto);
        }

        return routeDtos;

    }

    @Override
    public Route assignRouteToBus(Integer busId, Integer routeId, String token) throws LoginException, BusException, RouteException {

        adminAuthentication.authenticateAdminLoginSession(token);

        Bus bus = busService.authenticateBus(busId);

        Route route = authenticateRoute(routeId);

        route.getBuses().add(bus);

        bus.setRoute(route);

        busRepo.save(bus);

        return routeRepo.save(route);

    }

    private Route authenticateRoute(Integer rId) throws RouteException{

        Optional<Route> routeOpt = routeRepo.findById(rId);

        if (routeOpt.isEmpty()){
            throw new RouteException("Route does not exists with route id : "+rId);
        }

        return routeOpt.get();

    }

    private RouteDTO getRouteDto(Route route){

        RouteDTO routeDTO = new RouteDTO();

        routeDTO.setRouteFrom(route.getRouteFrom());
        routeDTO.setDistance(route.getDistance());
        routeDTO.setRouteTo(route.getRouteTo());

        return routeDTO;

    }

}
