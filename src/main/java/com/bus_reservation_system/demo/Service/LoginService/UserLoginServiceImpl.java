package com.bus_reservation_system.demo.Service.LoginService;

import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.UserException;
import com.bus_reservation_system.demo.Models.Login;
import com.bus_reservation_system.demo.Models.User;
import com.bus_reservation_system.demo.Models.UserCurrentSession;
import com.bus_reservation_system.demo.Repository.UserLoginRepo;
import com.bus_reservation_system.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

public class UserLoginServiceImpl implements UserLoginService{

    @Autowired
    UserLoginRepo userLoginRepo;

    UserRepo userRepo;
    @Override
    public String loginUser(Login loginDetails) throws LoginException,UserException {
        Optional<User> opt = userRepo.findByEmail(loginDetails.getEmail());

        if(opt.isEmpty()){
            throw new UserException("User does not exists with email : "+loginDetails.getEmail());
        }
        User user = opt.get();

        if(user.getPassword().equals(loginDetails.getPassword())==false){
            throw new LoginException("Wrong password");
        }

        Optional<UserCurrentSession> opt2 = userLoginRepo.findById(user.getUserid());

        if(opt2.isPresent()){
            throw new LoginException("User is already logged in with email ");
        }

        String key = randomStringGenerator();

        UserCurrentSession userCurrentSession = new UserCurrentSession();
        userCurrentSession.setKey(key);
        userCurrentSession.setUserId(user.getUserid());
        userCurrentSession.setLocalDateTime(LocalDateTime.now());

        UserCurrentSession returnedSession = userLoginRepo.save(userCurrentSession);

        return returnedSession.getKey();


    }
    public static String randomStringGenerator() {

        // length is bounded by 256 Character
        int n=10;
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString
                = new String(array, Charset.forName("UTF-8"));

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
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
