package com.bus_reservation_system.demo.Service.LoginService.User;

import com.bus_reservation_system.demo.ExceptionHandler.AdminException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.UserException;
import com.bus_reservation_system.demo.Models.Login;
import lombok.extern.java.Log;

public interface UserLoginService {
    public String loginUser(Login loginDetails) throws LoginException, UserException;

    public boolean logout(String key) throws LoginException;

}
