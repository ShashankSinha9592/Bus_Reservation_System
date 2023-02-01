package com.bus_reservation_system.demo.Service.AdminService;

import com.bus_reservation_system.demo.ExceptionHandler.AdminException;
import com.bus_reservation_system.demo.Models.Admin;
import com.bus_reservation_system.demo.Repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepo adminRepo;

    @Override
    public Admin registerAdmin(Admin admin) throws AdminException{

        Optional<Admin> adminOpt = adminRepo.findByEmail(admin.getEmail());
        System.out.println("hello admin service");
        if(adminOpt.isEmpty()){
            return adminRepo.save(admin);
        }
        else{
            throw new AdminException("email already exists");
        }

    }

    @Override
    public boolean checkToken(String token, String requestToken) throws AdminException {

        if(!token.equals(requestToken)) {
            throw new AdminException("Invalid token");
        }
        else {
            return true;
        }
    }
}
