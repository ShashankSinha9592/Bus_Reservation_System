package com.bus_reservation_system.demo.Service.Route;

import com.bus_reservation_system.demo.DTO.RouteDTO;
import com.bus_reservation_system.demo.ExceptionHandler.BusException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.RouteException;
import com.bus_reservation_system.demo.Models.Route;

import java.util.List;

public interface RouteService {

    public RouteDTO addRoute(Route route, String key) throws RouteException, LoginException;

    public Route updateRoute(Route route, String key) throws RouteException, LoginException;

    public RouteDTO viewRoute(Integer routeId, String key) throws RouteException, LoginException;

    public Route deleteRoute(Integer routeId, String key) throws RouteException, LoginException;

    public List<RouteDTO> viewAllRoute(String key ) throws LoginException, RouteException;

    public Route assignRouteToBus(Integer busId, Integer routeId, String key) throws LoginException, BusException, RouteException;




}
