package com.bus_reservation_system.demo.Service.User;

import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.UserException;
import com.bus_reservation_system.demo.Models.AdminCurrentSession;
import com.bus_reservation_system.demo.Models.User;
import com.bus_reservation_system.demo.Models.UserCurrentSession;
import com.bus_reservation_system.demo.Repository.AdminLoginRepo;
import com.bus_reservation_system.demo.Repository.UserLoginRepo;
import com.bus_reservation_system.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserLoginRepo userLoginRepo;

    @Autowired
    AdminLoginRepo adminLoginRepo;


    @Override
    public User addUser(User user) throws UserException {

        Optional<User> userOpt = userRepo.findByEmail(user.getEmail());

        if(userOpt.isPresent()){
            throw new UserException("User already exists with email : "+user.getEmail());
        }

        return userRepo.save(user);

    }

    @Override
    public User updateUser(User user, String key) throws LoginException, UserException {

        Optional<UserCurrentSession> userSessionOpt = userLoginRepo.findByToken(key);

        if(userSessionOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        Optional<User> savedUserOpt = userRepo.findById(user.getUserid());

        if(savedUserOpt.isEmpty()){
            throw new UserException("User does not exists with user id : "+user.getUserid());
        }

        return userRepo.save(user);



    }

    @Override
    public User viewUser(Integer uId, String key,String check) throws LoginException, UserException {

        if(checkUser(check,key)==false){
            throw new LoginException("Invalid url or please log in");
        }

        Optional<User> userOpt = userRepo.findById(uId);

        if(userOpt.isEmpty()){
            throw new UserException("User does not exists with user id : "+uId);
        }

        return userOpt.get();



    }

    @Override
    public User deleteUser(Integer uId, String key) throws UserException, LoginException {

        Optional<AdminCurrentSession> adminSessionOpt = adminLoginRepo.findByToken(key);

        if(adminSessionOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        Optional<User> userOpt = userRepo.findById(uId);

        if(userOpt.isEmpty()){
            throw new UserException("user does not exists with user id : "+uId);
        }

        Optional<UserCurrentSession> userSessionOpt = userLoginRepo.findById(uId);

        if(userSessionOpt.isPresent()){
            UserCurrentSession userCurrentSession = userSessionOpt.get();
            userLoginRepo.delete(userCurrentSession);
        }

        User user = userOpt.get();

        userRepo.delete(user);

        return user;

    }

    @Override
    public List<User> viewAllUser(String key) throws UserException, LoginException {

        Optional<AdminCurrentSession> adminSessionOpt = adminLoginRepo.findByToken(key);

        if(adminSessionOpt.isEmpty()){
            throw new LoginException("Please login first");
        }

        List<User> users = userRepo.findAll();

        if(users.isEmpty()){
            throw new UserException("No users found");
        }

        return users;

    }

    private boolean checkUser(String check, String key){

        if(check.equals("admin")){
            Optional<AdminCurrentSession> adminSessionOpt = adminLoginRepo.findByToken(key);
            if (adminSessionOpt.isEmpty()){
                return false;
            }
            else{
                return true;
            }
        }
        else if(check.equals("user")){
            Optional<UserCurrentSession> userSessionOpt = userLoginRepo.findByToken(key);
            if (userSessionOpt.isEmpty()){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }


    }

}
