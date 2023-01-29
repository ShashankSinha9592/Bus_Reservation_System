package com.bus_reservation_system.demo.Service.LogoutService;

import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.Models.AdminCurrentSession;
import com.bus_reservation_system.demo.Models.UserCurrentSession;
import com.bus_reservation_system.demo.Repository.AdminLoginRepo;
import com.bus_reservation_system.demo.Repository.UserLoginRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AdminLogout {

    @Autowired
    AdminLoginRepo adminLoginRepo;

    public boolean logout(String key) throws Exception{

        Optional<AdminCurrentSession> opt = adminLoginRepo.findByKey(key);

        if(opt.isEmpty()){
            throw new LoginException("Token/key does not exists");
        }

        AdminCurrentSession adminCurrentSession = opt.get();

        adminLoginRepo.delete(adminCurrentSession);

        return true;


    }

}
