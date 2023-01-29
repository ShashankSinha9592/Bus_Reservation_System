package com.bus_reservation_system.demo.Service.LogoutService;

import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.Models.UserCurrentSession;
import com.bus_reservation_system.demo.Repository.UserLoginRepo;
import com.bus_reservation_system.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserLogout {

    @Autowired
    UserLoginRepo userLoginRepo;

    public boolean logout(String key) throws Exception{

        Optional<UserCurrentSession> opt = userLoginRepo.findByKey(key);

        if(opt.isEmpty()){
            throw new LoginException("Token/key does not exists");
        }

        UserCurrentSession userCurrentSession = opt.get();

        userLoginRepo.delete(userCurrentSession);

        return true;




    }

}
