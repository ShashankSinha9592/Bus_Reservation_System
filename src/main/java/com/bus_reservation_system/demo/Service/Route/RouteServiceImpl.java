package com.bus_reservation_system.demo.Service.Route;

import com.bus_reservation_system.demo.DTO.RouteDTO;
import com.bus_reservation_system.demo.ExceptionHandler.BusException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.RouteException;
import com.bus_reservation_system.demo.Models.Bus;
import com.bus_reservation_system.demo.Models.Route;
import com.bus_reservation_system.demo.Repository.*;
import com.bus_reservation_system.demo.Service.Bus.BusService;
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
    BusRepo busRepo;

    @Autowired
    RouteRepo routeRepo;

    @Override
    public RouteDTO addRoute(Route route) throws RouteException, LoginException {

        Route savedRoute = routeRepo.save(route);

        return getRouteDto(savedRoute);

    }

    @Override
    public Route updateRoute(Route route) throws RouteException, LoginException {

        Route savedRoute = authenticateRoute(route.getRouteId());

        route.setBuses(savedRoute.getBuses());

        return routeRepo.save(route);

    }

    @Override
    public RouteDTO viewRoute(Integer routeId) throws RouteException, LoginException {

        Route savedRoute = authenticateRoute(routeId);

        return getRouteDto(savedRoute);

    }

    @Override
    public Route deleteRoute(Integer routeId) throws RouteException, LoginException {

        Route route = authenticateRoute(routeId);

        routeRepo.delete(route);

        return route;


    }

    @Override
    public List<RouteDTO> viewAllRoute() throws LoginException, RouteException {

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
    public Route assignRouteToBus(Integer busId, Integer routeId) throws LoginException, BusException, RouteException {

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
