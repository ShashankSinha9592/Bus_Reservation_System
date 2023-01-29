package com.bus_reservation_system.demo.ExceptionHandler;

public class BusException extends RuntimeException{
    public BusException() {
    }

    public BusException(String message) {
        super(message);
    }
}
