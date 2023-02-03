package com.bus_reservation_system.demo.Service.LoginService.Admin;

import com.bus_reservation_system.demo.ExceptionHandler.AdminException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.Models.*;
import com.bus_reservation_system.demo.Repository.AdminLoginRepo;
import com.bus_reservation_system.demo.Repository.AdminRepo;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    AdminLoginRepo adminLoginRepo;

    @Autowired
    AdminRepo adminRepo;


    @Override
    public String loginAdmin(Login loginDetails) throws LoginException {
        Optional<Admin> opt = adminRepo.findByEmail(loginDetails.getEmail());

        if (opt.isEmpty()) {
            throw new AdminException("Admin does not exists with email : " + loginDetails.getEmail());
        }
        Admin admin = opt.get();

        if (!admin.getPassword().equals(loginDetails.getPassword())) {
            throw new LoginException("Wrong password");
        }

        Optional<AdminCurrentSession> opt2 = adminLoginRepo.findById(admin.getAdminId());

        if (opt2.isPresent()) {
            throw new LoginException("Admin is already logged in with this email ");
        }

        String key = RandomString.make(10);

        AdminCurrentSession adminCurrentSession = new AdminCurrentSession();

        adminCurrentSession.setToken(key);
        adminCurrentSession.setAdminId(admin.getAdminId());
        adminCurrentSession.setTime(LocalDateTime.now());

        System.out.println(adminCurrentSession);

        AdminCurrentSession returnedSession = adminLoginRepo.save(adminCurrentSession);
        System.out.println("checking");
        return returnedSession.getToken();

    }

    public boolean logout(String key) throws LoginException {

        Optional<AdminCurrentSession> opt = adminLoginRepo.findByToken(key);

        if (opt.isEmpty()) {
            throw new LoginException("Token/key does not exists");
        }

        AdminCurrentSession adminCurrentSession = opt.get();

        adminLoginRepo.delete(adminCurrentSession);

        return true;


    }

}
