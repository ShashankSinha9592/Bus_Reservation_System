package com.bus_reservation_system.demo.Service.LoginService;

import com.bus_reservation_system.demo.ExceptionHandler.AdminException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.UserException;
import com.bus_reservation_system.demo.Models.*;
import com.bus_reservation_system.demo.Repository.AdminLoginRepo;
import com.bus_reservation_system.demo.Repository.AdminRepo;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AdminLoginServiceImpl implements AdminLoginService{

    @Autowired
    AdminLoginRepo adminLoginRepo;

    @Autowired
    AdminRepo adminRepo;


    @Override
    public String loginAdmin(Login loginDetails) throws LoginException {
        Optional<Admin> opt = adminRepo.findByEmail(loginDetails.getEmail());

        if(opt.isEmpty()){
            throw new AdminException("Admin does not exists with email : "+loginDetails.getEmail());
        }
        Admin admin = opt.get();

        if(!admin.getPassword().equals(loginDetails.getPassword())){
            throw new LoginException("Wrong password");
        }

        Optional<AdminCurrentSession> opt2 = adminLoginRepo.findById(admin.getAdminId());

        if(opt2.isPresent()){
            throw new LoginException("Admin is already logged in with this email ");
        }

        String key = RandomString.make(6);

        AdminCurrentSession adminCurrentSession = new AdminCurrentSession();

        adminCurrentSession.setToken(key);
        adminCurrentSession.setAdminId(admin.getAdminId());
        adminCurrentSession.setTime(LocalDateTime.now());

        System.out.println(adminCurrentSession);

        AdminCurrentSession returnedSession = adminLoginRepo.save(adminCurrentSession);
        System.out.println("checking");
        return returnedSession.getToken();

    }
    public boolean logout(String key) throws LoginException{

        Optional<AdminCurrentSession> opt = adminLoginRepo.findByToken(key);

        if(opt.isEmpty()){
            throw new LoginException("Token/key does not exists");
        }

        AdminCurrentSession adminCurrentSession = opt.get();

        adminLoginRepo.delete(adminCurrentSession);

        return true;


    }
    public static String randomStringGenerator() {

        // length is bounded by 256 Character
        int n=10;
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString
                = new String(array, StandardCharsets.UTF_8);

        StringBuffer r = new StringBuffer();

        for (int k = 0; k < randomString.length(); k++) {

            char ch = randomString.charAt(k);

            if (((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9'))
                    && (n > 0)) {

                r.append(ch);
                n--;
            }
        }

        // return the resultant string
        return r.toString();
    }

}
