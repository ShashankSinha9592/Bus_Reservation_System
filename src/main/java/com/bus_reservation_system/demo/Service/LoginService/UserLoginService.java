package com.bus_reservation_system.demo.Service.LoginService;

import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.UserException;
import com.bus_reservation_system.demo.Models.Login;

public interface UserLoginService {
    public String loginUser(Login loginDetails) throws LoginException, UserException;
}
