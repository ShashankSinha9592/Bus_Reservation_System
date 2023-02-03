package com.bus_reservation_system.demo.Service.User;

import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.UserException;
import com.bus_reservation_system.demo.Models.User;
import com.bus_reservation_system.demo.Models.UserCurrentSession;
import com.bus_reservation_system.demo.Repository.UserLoginRepo;
import com.bus_reservation_system.demo.Repository.UserRepo;
import com.bus_reservation_system.demo.Service.LoginService.Admin.AdminAuthentication;
import com.bus_reservation_system.demo.Service.LoginService.User.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserAuthentication userAuthentication;

    @Autowired
    AdminAuthentication adminAuthentication;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserLoginRepo userLoginRepo;

    @Override
    public User addUser(User user) throws UserException {

        Optional<User> userOpt = userRepo.findByEmail(user.getEmail());

        if(userOpt.isPresent()){
            throw new UserException("User already exists with email : "+user.getEmail());
        }

        return userRepo.save(user);

    }

    @Override
    public User updateUser(User user, String token) throws LoginException, UserException {

        userAuthentication.authenticateUserLoginSession(token);

        userAuthentication.authenticateUser(user.getUserid());

        return userRepo.save(user);

    }

    @Override
    public User viewUser(Integer uId, String token,String check) throws LoginException, UserException {

        checkUser(check,token);

        return userAuthentication.authenticateUser(uId);

    }

    @Override
    public User deleteUser(Integer uId, String token) throws UserException, LoginException {

        adminAuthentication.authenticateAdminLoginSession(token);

        User user = userAuthentication.authenticateUser(uId);

        Optional<UserCurrentSession> userSessionOpt = userLoginRepo.findById(uId);

        if(userSessionOpt.isPresent()){
            UserCurrentSession userCurrentSession = userSessionOpt.get();
            userLoginRepo.delete(userCurrentSession);
        }

        userRepo.delete(user);

        return user;

    }

    @Override
    public List<User> viewAllUser(String token) throws UserException, LoginException {

        adminAuthentication.authenticateAdminLoginSession(token);

        List<User> users = userRepo.findAll();

        if(users.isEmpty()){
            throw new UserException("No users found");
        }

        return users;

    }

    public void checkUser(String check, String token)throws LoginException {

        if(check.equals("admin")){
            adminAuthentication.authenticateAdminLoginSession(token);
        }
        else if(check.equals("user")){
            userAuthentication.authenticateUserLoginSession(token);
        }
        else throw new RuntimeException("Invalid url");

    }

}
