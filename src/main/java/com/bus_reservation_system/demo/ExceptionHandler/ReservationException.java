package com.bus_reservation_system.demo.ExceptionHandler;

public class ReservationException extends RuntimeException{
    public ReservationException() {
    }

    public ReservationException(String message) {
        super(message);
    }
}
