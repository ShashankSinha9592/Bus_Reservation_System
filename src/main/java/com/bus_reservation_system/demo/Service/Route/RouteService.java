package com.bus_reservation_system.demo.Service.Route;

import com.bus_reservation_system.demo.DTO.RouteDTO;
import com.bus_reservation_system.demo.ExceptionHandler.BusException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.RouteException;
import com.bus_reservation_system.demo.Models.Route;

import java.util.List;

public interface RouteService {

    public RouteDTO addRoute(Route route) throws RouteException, LoginException;

    public Route updateRoute(Route route) throws RouteException, LoginException;

    public RouteDTO viewRoute(Integer routeId) throws RouteException, LoginException;

    public Route deleteRoute(Integer routeId) throws RouteException, LoginException;

    public List<RouteDTO> viewAllRoute() throws LoginException, RouteException;

    public Route assignRouteToBus(Integer busId, Integer routeId) throws LoginException, BusException, RouteException;




}
