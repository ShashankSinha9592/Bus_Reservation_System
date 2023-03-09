package com.bus_reservation_system.demo.Service.Feedback;

import com.bus_reservation_system.demo.ExceptionHandler.BusException;
import com.bus_reservation_system.demo.ExceptionHandler.FeedbackException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.Models.Feedback;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface FeedbackService {

    public Feedback addFeedBack(Feedback feedback , Integer busId, Authentication authentication) throws LoginException, FeedbackException, BusException;

    public Feedback updateFeedback(Feedback feedback, Authentication authentication) throws FeedbackException, LoginException;

    public Feedback viewFeedbackById(Integer fId) throws FeedbackException , LoginException;

    public List<Feedback> viewAllFeedback() throws FeedbackException , LoginException;

    public List<Feedback> viewAllFeedbacksOfBus(Integer busId) throws BusException, LoginException, FeedbackException;

}
