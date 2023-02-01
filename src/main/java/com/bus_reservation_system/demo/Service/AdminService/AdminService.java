package com.bus_reservation_system.demo.Service.AdminService;

import com.bus_reservation_system.demo.ExceptionHandler.AdminException;
import com.bus_reservation_system.demo.Models.Admin;

public interface AdminService {

    public Admin registerAdmin(Admin admin) throws AdminException;

    public boolean checkToken(String token , String requestToken)throws AdminException;

}
