package com.bus_reservation_system.demo.ExceptionHandler;

public class RouteException extends RuntimeException{
    public RouteException() {
    }

    public RouteException(String message) {
        super(message);
    }
}
