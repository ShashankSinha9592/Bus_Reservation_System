package com.bus_reservation_system.demo.Service.LoginService.User;

import com.bus_reservation_system.demo.ExceptionHandler.AdminException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;

import com.bus_reservation_system.demo.ExceptionHandler.UserException;
import com.bus_reservation_system.demo.Models.User;
import com.bus_reservation_system.demo.Models.UserCurrentSession;

public interface UserAuthentication {

    public UserCurrentSession authenticateUserLoginSession(String token) throws LoginException ;

    public User authenticateUser(Integer userId) throws UserException;

}
