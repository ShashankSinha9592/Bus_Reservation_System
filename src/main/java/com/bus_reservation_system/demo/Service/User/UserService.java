package com.bus_reservation_system.demo.Service.User;

import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.UserException;
import com.bus_reservation_system.demo.Models.User;
import lombok.extern.java.Log;

import java.util.List;

public interface UserService {

    public User addUser(User user) throws UserException;

    public User updateUser(User user , String key) throws LoginException, UserException;

    public User viewUser(Integer uId, String key,String check) throws LoginException, UserException;

    public User deleteUser(Integer uId, String key) throws UserException, LoginException;

    public List<User> viewAllUser(String key) throws UserException, LoginException;

}
