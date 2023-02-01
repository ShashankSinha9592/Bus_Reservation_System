package com.bus_reservation_system.demo.Service.Feedback;

import com.bus_reservation_system.demo.ExceptionHandler.*;
import com.bus_reservation_system.demo.Models.*;
import com.bus_reservation_system.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService{

    @Autowired
    FeedbackRepo feedbackRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserLoginRepo userLoginRepo;

    @Autowired
    BusRepo busRepo;

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    AdminLoginRepo adminLoginRepo;

    @Override
    public Feedback addFeedBack(Feedback feedback, String key, Integer busId) throws LoginException, FeedbackException , BusException {

        Optional<UserCurrentSession> sessionOpt = userLoginRepo.findByToken(key);

        if(sessionOpt.isEmpty()){
            throw new LoginException("Please login first");
        }
        Optional<Bus> busOpt = busRepo.findById(busId);

        if(busOpt.isEmpty()){
            throw new BusException("Bus does not exists with bus id : "+busId);
        }
        Bus bus = busOpt.get();

        Optional<User> userOpt = userRepo.findById(sessionOpt.get().getUserId());

        User user = userOpt.get();

        feedback.setBus(bus);
        feedback.setUser(user);
        userRepo.save(user);
        busRepo.save(bus);
        return feedbackRepo.save(feedback);

    }

    @Override
    public Feedback updateFeedback(Feedback feedback, String key) throws FeedbackException, LoginException {
        Optional<UserCurrentSession> sessionOpt = userLoginRepo.findByToken(key);

        if(sessionOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        Optional<User> userOpt = userRepo.findById(sessionOpt.get().getUserId());

        User user = userOpt.get();

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
    public Feedback viewFeedbackById(Integer fId, String key) throws FeedbackException, LoginException {
           Optional<AdminCurrentSession> adminOpt =  adminLoginRepo.findByToken(key);

           if (adminOpt.isEmpty()){
               throw new LoginException("Please login first");
           }

           Optional<Feedback> feedbackOpt = feedbackRepo.findById(fId);

           if(feedbackOpt.isEmpty()){
               throw new FeedbackException("Feedback does not exists with feedback id : "+fId);
           }
           return feedbackOpt.get();

    }

    @Override
    public List<Feedback> viewAllFeedback(String key) throws FeedbackException, LoginException {

        Optional<AdminCurrentSession> adminOpt =  adminLoginRepo.findByToken(key);

        if (adminOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        List<Feedback> feedbacks = feedbackRepo.findAll();

        if(feedbacks.isEmpty()){
            throw new FeedbackException("No feedbacks found");
        }

        return feedbacks;

    }

    @Override
    public List<Feedback> viewAllFeedbacksOfBus(Integer busId, String key, String check) throws BusException, LoginException, FeedbackException {

        Optional<AdminCurrentSession> adminOpt;
        Optional<UserCurrentSession> userOpt;
        if(check.equals("admin")) {
            adminOpt = adminLoginRepo.findByToken(key);
            if(adminOpt.isEmpty()){
                throw new AdminException("Please login first");
            }
        }
        if(check.equals("user")){
            userOpt = userLoginRepo.findByToken(key);
            if(userOpt.isEmpty()){
                throw new UserException("Please login first");
            }
        }

        Optional<Bus> busOpt = busRepo.findById(busId);

        if(busOpt.isEmpty()){
            throw new BusException("Bus does not exists with bus id : "+busId);
        }

        Bus bus = busOpt.get();

        List<Feedback> feedbacks = bus.getFeedbacks();

        if(feedbacks.isEmpty()){
            throw new FeedbackException("No feedbacks found");
        }

        return feedbacks;

    }
}
