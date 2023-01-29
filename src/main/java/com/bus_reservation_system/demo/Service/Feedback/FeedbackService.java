package com.bus_reservation_system.demo.Service.Feedback;

import com.bus_reservation_system.demo.ExceptionHandler.BusException;
import com.bus_reservation_system.demo.ExceptionHandler.FeedbackException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.Models.Feedback;
import com.bus_reservation_system.demo.Models.Login;
import lombok.extern.java.Log;

import java.util.List;

public interface FeedbackService {

    public Feedback addFeedBack(Feedback feedback , String key, Integer busId) throws LoginException, FeedbackException, BusException;

    public Feedback updateFeedback(Feedback feedback , String key) throws FeedbackException, LoginException;

    public Feedback viewFeedbackById(Integer fId, String key) throws FeedbackException , LoginException;

    public List<Feedback> viewAllFeedback(String key) throws FeedbackException , LoginException;

    public List<Feedback> viewAllFeedbacksOfBus(Integer busId, String key, String check) throws BusException, LoginException, FeedbackException;

}
