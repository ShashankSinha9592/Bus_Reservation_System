package com.bus_reservation_system.demo.Controller;


import com.bus_reservation_system.demo.Models.Login;
import com.bus_reservation_system.demo.Service.LoginService.Admin.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AdminLoginController {

    @Autowired
    AdminLoginService adminLoginService;


    @PostMapping("/admin/login")
    public ResponseEntity<String> loginAdminHandler(@Valid  @RequestBody Login login){
        String token = adminLoginService.loginAdmin(login);

        return new ResponseEntity<>(token, HttpStatus.ACCEPTED);

    }

    @GetMapping("admin/logout/{token}")
    public ResponseEntity<String> logoutAdminHandler(@PathVariable String token){

        boolean flag = adminLoginService.logout(token);

        String message = null;
        if(flag){

            message = "Logout successfull";

        }
        return new ResponseEntity<>(message,HttpStatus.OK);

    }


}
