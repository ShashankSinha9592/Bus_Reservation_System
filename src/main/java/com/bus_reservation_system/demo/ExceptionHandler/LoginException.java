package com.bus_reservation_system.demo.ExceptionHandler;

public class LoginException extends RuntimeException{
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }
}
