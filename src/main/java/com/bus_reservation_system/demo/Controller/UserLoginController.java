package com.bus_reservation_system.demo.Controller;

import com.bus_reservation_system.demo.Models.Login;
import com.bus_reservation_system.demo.Service.LoginService.AdminLoginService;
import com.bus_reservation_system.demo.Service.LoginService.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserLoginController {

    @Autowired
    UserLoginService userLoginService;


    @PostMapping("/user/login")
    public ResponseEntity<String> loginAdminHandler(@Valid @RequestBody Login login){
        String token = userLoginService.loginUser(login);
        System.out.println("controller");
        return new ResponseEntity<>(token, HttpStatus.ACCEPTED);

    }

    @GetMapping("user/logout/{token}")
    public ResponseEntity<String> logoutAdminHandler(@PathVariable String token){

        boolean flag = userLoginService.logout(token);

        String message = null;
        if(flag){

            message = "Logout successfull";

        }
        return new ResponseEntity<>(message,HttpStatus.OK);

    }

}
