package com.bus_reservation_system.demo.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<MyErrorDetails> loginExceptionHandler(LoginException exc, WebRequest req){
        MyErrorDetails err = new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(FeedbackException.class)
    public ResponseEntity<MyErrorDetails> feedbackExceptionHandler(FeedbackException exc, WebRequest req){
        MyErrorDetails err = new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(ReservationException.class)
    public ResponseEntity<MyErrorDetails> reservationExceptionHandler(ReservationException exc, WebRequest req){
        MyErrorDetails err = new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(RouteException.class)
    public ResponseEntity<MyErrorDetails> routeExceptionHandler(RouteException exc, WebRequest req){
        MyErrorDetails err = new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(UserException.class)
    public ResponseEntity<MyErrorDetails> userExceptionHandler(UserException exc, WebRequest req){
        MyErrorDetails err = new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(BusException.class)
    public ResponseEntity<MyErrorDetails> busExceptionHandler(BusException exc, WebRequest req){
        MyErrorDetails err = new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<MyErrorDetails> sqlExceptionHandler(SQLException exc, WebRequest req){
        MyErrorDetails err = new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MyErrorDetails> busExceptionHandler(BadCredentialsException exc, WebRequest req){
        MyErrorDetails err = new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> genericExceptionHandler(Exception exc, WebRequest req){
        MyErrorDetails err = new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyErrorDetails> methodArgumentExceptionHandler(MethodArgumentNotValidException me, WebRequest req)  {


        MyErrorDetails err=new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setDescription(req.getDescription(false));
        err.setMessage(me.getBindingResult().getFieldError().getDefaultMessage());


        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<MyErrorDetails> noHandlerExceptionHandler(NoHandlerFoundException me, WebRequest req)  {


        MyErrorDetails err=new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setDescription(req.getDescription(false));
        err.setMessage(me.getMessage());


        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }

}

