package com.bus_reservation_system.demo.Service.LoginService.Admin;

import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.Models.Login;
import lombok.extern.java.Log;

public interface AdminLoginService {

    public String loginAdmin(Login loginDetails) throws LoginException;

    public boolean logout(String key) throws LoginException;

}
