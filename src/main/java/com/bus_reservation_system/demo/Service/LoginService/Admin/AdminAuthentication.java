package com.bus_reservation_system.demo.Service.LoginService.Admin;

import com.bus_reservation_system.demo.ExceptionHandler.AdminException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.Models.Admin;
import com.bus_reservation_system.demo.Models.AdminCurrentSession;

public interface AdminAuthentication {
    public AdminCurrentSession authenticateAdminLoginSession(String token) throws LoginException;

    public Admin authenticateAdmin(Integer adminId) throws AdminException;

}
