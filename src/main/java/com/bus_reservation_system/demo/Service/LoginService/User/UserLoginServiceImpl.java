package com.bus_reservation_system.demo.Service.LoginService.User;

import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.UserException;
import com.bus_reservation_system.demo.Models.Login;
import com.bus_reservation_system.demo.Models.User;
import com.bus_reservation_system.demo.Models.UserCurrentSession;
import com.bus_reservation_system.demo.Repository.UserLoginRepo;
import com.bus_reservation_system.demo.Repository.UserRepo;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserLoginServiceImpl implements UserLoginService{

    @Autowired
    UserLoginRepo userLoginRepo;
    @Autowired
    UserRepo userRepo;
    @Override
    public String loginUser(Login loginDetails) throws LoginException,UserException {

        Optional<User> opt = userRepo.findByEmail(loginDetails.getEmail());

        if(opt.isEmpty()){
            throw new UserException("User does not exists with email : "+loginDetails.getEmail());
        }
        User user = opt.get();

        if(!user.getPassword().equals(loginDetails.getPassword())){
            throw new LoginException("Wrong password");
        }

        Optional<UserCurrentSession> opt2 = userLoginRepo.findById(user.getUserid());

        if(opt2.isPresent()){
            throw new LoginException("User is already logged in with email ");
        }

        String key = RandomString.make(10);

        UserCurrentSession userCurrentSession = new UserCurrentSession();
        userCurrentSession.setToken(key);
        userCurrentSession.setUserId(user.getUserid());
        userCurrentSession.setTime(LocalDateTime.now());
        System.out.println(loginDetails);
        System.out.println(userCurrentSession);
        UserCurrentSession returnedSession = userLoginRepo.save(userCurrentSession);
        System.out.println("check");
        return returnedSession.getToken();


    }

    public boolean logout(String key) throws LoginException{

        Optional<UserCurrentSession> opt = userLoginRepo.findByToken(key);

        if(opt.isEmpty()){
            throw new LoginException("Token/key does not exists");
        }

        UserCurrentSession userCurrentSession = opt.get();

        userLoginRepo.delete(userCurrentSession);

        return true;

    }

}
