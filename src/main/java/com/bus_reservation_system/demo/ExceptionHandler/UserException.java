package com.bus_reservation_system.demo.ExceptionHandler;

public class UserException extends RuntimeException{
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }
}
