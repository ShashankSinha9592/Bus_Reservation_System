package com.bus_reservation_system.demo.Service.User;

import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.UserException;
import com.bus_reservation_system.demo.Models.User;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {

    public User addUser(User user) throws UserException;

    public User updateUser(User user, Authentication authentication) throws  UserException;

    public User viewUser(Integer uId, Authentication authentication) throws  UserException;

    public User deleteUser(Integer uId, Authentication authentication) throws UserException;

    public List<User> viewAllUser() throws UserException;

    public User validateUserByEmail(String email)throws UserException;

    public User validateUserById(Integer userId)throws UserException;
}
