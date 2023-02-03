package com.bus_reservation_system.demo.Service.LoginService.Admin;

import com.bus_reservation_system.demo.ExceptionHandler.AdminException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.Models.Admin;
import com.bus_reservation_system.demo.Models.AdminCurrentSession;
import com.bus_reservation_system.demo.Repository.AdminLoginRepo;
import com.bus_reservation_system.demo.Repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminAuthenticationImpl implements AdminAuthentication{

    @Autowired
    AdminLoginRepo adminLoginRepo;

    @Autowired
    AdminRepo adminRepo;

    @Override
    public AdminCurrentSession authenticateAdminLoginSession(String token) throws LoginException {

        Optional<AdminCurrentSession> adminSessionOpt = adminLoginRepo.findByToken(token);

        if(adminSessionOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        return adminSessionOpt.get();

    }

    @Override
    public Admin authenticateAdmin(Integer adminId) throws AdminException {

        Optional<Admin> adminOpt = adminRepo.findById(adminId);

        if (adminOpt.isEmpty()){
            throw new AdminException("Admin does not exists with admin id : "+adminId);
        }

        return adminOpt.get();

    }
}
