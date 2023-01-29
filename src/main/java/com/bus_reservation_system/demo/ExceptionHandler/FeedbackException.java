package com.bus_reservation_system.demo.ExceptionHandler;

public class FeedbackException extends RuntimeException{
    public FeedbackException() {
    }

    public FeedbackException(String message) {
        super(message);
    }
}
