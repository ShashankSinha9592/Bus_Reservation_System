package com.bus_reservation_system.demo.Service.LoginService.User;

import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.UserException;
import com.bus_reservation_system.demo.Models.User;
import com.bus_reservation_system.demo.Models.UserCurrentSession;
import com.bus_reservation_system.demo.Repository.UserLoginRepo;
import com.bus_reservation_system.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthenticationImpl implements UserAuthentication{

    @Autowired
    UserLoginRepo userLoginRepo;

    @Autowired
    UserRepo userRepo;
    @Override
    public UserCurrentSession authenticateUserLoginSession(String token) throws LoginException {

        Optional<UserCurrentSession> userSessionOpt = userLoginRepo.findByToken(token);

        if(userSessionOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        return userSessionOpt.get();

    }

    @Override
    public User authenticateUser(Integer userId) throws UserException {

        Optional<User> userOpt = userRepo.findById(userId);

        if(userOpt.isEmpty()){
            throw new UserException("User does not exists with user id : "+userId);
        }

        return userOpt.get();
    }
}
