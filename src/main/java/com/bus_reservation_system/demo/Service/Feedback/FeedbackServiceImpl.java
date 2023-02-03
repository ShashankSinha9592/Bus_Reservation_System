package com.bus_reservation_system.demo.Service.Feedback;

import com.bus_reservation_system.demo.ExceptionHandler.*;
import com.bus_reservation_system.demo.Models.*;
import com.bus_reservation_system.demo.Repository.*;
import com.bus_reservation_system.demo.Service.Bus.BusService;
import com.bus_reservation_system.demo.Service.LoginService.Admin.AdminAuthentication;
import com.bus_reservation_system.demo.Service.LoginService.User.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService{

    @Autowired
    BusService busService;

    @Autowired
    UserAuthentication userAuthentication;

    @Autowired
    AdminAuthentication adminAuthentication;

    @Autowired
    FeedbackRepo feedbackRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    BusRepo busRepo;

    @Override
    public Feedback addFeedBack(Feedback feedback, String token, Integer busId) throws LoginException, FeedbackException , BusException {

        UserCurrentSession userCurrentSession = userAuthentication.authenticateUserLoginSession(token);

        Bus bus = busService.authenticateBus(busId);

        User user = userAuthentication.authenticateUser(userCurrentSession.getUserId());

        feedback.setBus(bus);

        feedback.setUser(user);

        bus.getFeedbacks().add(feedback);

        user.getFeedbacks().add(feedback);

        userRepo.save(user);

        busRepo.save(bus);

        return feedbackRepo.save(feedback);

    }

    @Override
    public Feedback updateFeedback(Feedback feedback, String token) throws FeedbackException, LoginException {

        UserCurrentSession userCurrentSession = userAuthentication.authenticateUserLoginSession(token);

        User user = userAuthentication.authenticateUser(userCurrentSession.getUserId());

        for (Feedback feedback1 :user.getFeedbacks()){
            if(feedback1.getFeedbackId()==feedback.getFeedbackId()){
                feedback.setUser(user);
                feedback.setBus(feedback1.getBus());
                return feedbackRepo.save(feedback);
            }
        }
        throw new FeedbackException("None of your feedback has feedback id : "+feedback.getFeedbackId());

    }

    @Override
    public Feedback viewFeedbackById(Integer fId, String token) throws FeedbackException, LoginException {
           adminAuthentication.authenticateAdminLoginSession(token);

           return getFeedBack(fId);

    }

    @Override
    public List<Feedback> viewAllFeedback(String token) throws FeedbackException, LoginException {

        adminAuthentication.authenticateAdminLoginSession(token);

        List<Feedback> feedbacks = feedbackRepo.findAll();

        if(feedbacks.isEmpty()){
            throw new FeedbackException("No feedbacks found");
        }

        return feedbacks;

    }

    @Override
    public List<Feedback> viewAllFeedbacksOfBus(Integer busId, String token, String check) throws BusException, LoginException, FeedbackException {


        if(check.equals("admin")) {
            adminAuthentication.authenticateAdminLoginSession(token);
        }
        if(check.equals("user")){
           userAuthentication.authenticateUserLoginSession(token);
        }

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
