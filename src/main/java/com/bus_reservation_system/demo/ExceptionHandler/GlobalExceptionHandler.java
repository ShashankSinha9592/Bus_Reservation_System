package com.bus_reservation_system.demo.ExceptionHandler;


import com.bus_reservation_system.demo.Models.Bus;
import com.bus_reservation_system.demo.Models.Feedback;
import com.bus_reservation_system.demo.Models.Route;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
    @ExceptionHandler(AdminException.class)
    public ResponseEntity<MyErrorDetails> adminExceptionHandler(AdminException exc, WebRequest req){
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MyErrorDetails> httpExceptionHandler(HttpMessageNotReadableException me, WebRequest req)  {


        MyErrorDetails err=new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setDescription(req.getDescription(false));
        err.setMessage(me.getMessage());


        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }



    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<MyErrorDetails> sqlIntegrityExceptionHandler(SQLIntegrityConstraintViolationException exc , WebRequest req){
        MyErrorDetails myError = new MyErrorDetails();
        myError.setMessage(exc.getMessage());
        myError.setDescription((req.getDescription(false)));
        myError.setDateAndTime(LocalDateTime.now());

        return new ResponseEntity<>(myError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MyErrorDetails> constraintExceptionHandler(ConstraintViolationException exc , WebRequest req){
        MyErrorDetails myError = new MyErrorDetails();
        myError.setMessage(exc.getMessage());
        myError.setDescription((req.getDescription(false)));
        myError.setDateAndTime(LocalDateTime.now());

        return new ResponseEntity<>(myError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MyErrorDetails> httpMessageExceptionHandler(HttpMessageNotReadableException exc , WebRequest req){
        MyErrorDetails myError = new MyErrorDetails();
        myError.setMessage(exc.getHttpInputMessage().toString());
        myError.setDescription((req.getDescription(false)));
        myError.setDateAndTime(LocalDateTime.now());

        return new ResponseEntity<>(myError, HttpStatus.BAD_REQUEST);
    }

}

