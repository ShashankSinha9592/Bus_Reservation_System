package com.bus_reservation_system.demo.ExceptionHandler;

public class AdminException extends RuntimeException{
    public AdminException() {
    }

    public AdminException(String message) {
        super(message);
    }
}
