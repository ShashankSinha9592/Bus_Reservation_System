package com.bus_reservation_system.demo.Service.Feedback;

import com.bus_reservation_system.demo.ExceptionHandler.*;
import com.bus_reservation_system.demo.Models.*;
import com.bus_reservation_system.demo.Repository.*;
import com.bus_reservation_system.demo.Service.Bus.BusService;
import com.bus_reservation_system.demo.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService{

    @Autowired
    BusService busService;

    @Autowired
    FeedbackRepo feedbackRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    BusRepo busRepo;

    @Autowired
    UserService userService;

    @Override
    public Feedback addFeedBack(Feedback feedback, Integer busId, Authentication authentication) throws LoginException, FeedbackException , BusException {

        String email = authentication.getName();

        User user = userService.validateUserByEmail(email);

        Bus bus = busService.authenticateBus(busId);

        feedback.setBus(bus);

        feedback.setUser(user);

        bus.getFeedbacks().add(feedback);

        user.getFeedbacks().add(feedback);

        userRepo.save(user);

        busRepo.save(bus);

        return feedback;

    }

    @Override
    public Feedback updateFeedback(Feedback feedback, Authentication authentication) throws FeedbackException, LoginException {

        User user = userService.validateUserByEmail(authentication.getName());

        for (Feedback feedback1 :user.getFeedbacks()){

            if(feedback1.getFeedbackId()==feedback.getFeedbackId()){

                feedback.setUser(user);

                feedback.setBus(feedback1.getBus());

                return feedbackRepo.save(feedback);

            }
        }

        throw new FeedbackException("Invalid feedback id : "+feedback.getFeedbackId());

    }

    @Override
    public Feedback viewFeedbackById(Integer fId) throws FeedbackException, LoginException {

           return getFeedBack(fId);

    }

    @Override
    public List<Feedback> viewAllFeedback() throws FeedbackException, LoginException {

        List<Feedback> feedbacks = feedbackRepo.findAll();

        if(feedbacks.isEmpty()){

            throw new FeedbackException("No feedbacks found");

        }

        return feedbacks;

    }

    @Override
    public List<Feedback> viewAllFeedbacksOfBus(Integer busId) throws BusException, LoginException, FeedbackException {

        Bus bus = busService.authenticateBus(busId);

        List<Feedback> feedbacks = bus.getFeedbacks();

        if(feedbacks.isEmpty()){

            throw new FeedbackException("No feedbacks found");

        }

        return feedbacks;

    }

    private Feedback getFeedBack(Integer fId)throws FeedbackException{

        Optional<Feedback> feedbackOpt = feedbackRepo.findById(fId);

        if(feedbackOpt.isEmpty()){

            throw new FeedbackException("Feedback does not exists with feedback id : "+fId);

        }

        return feedbackOpt.get();

    }
}
