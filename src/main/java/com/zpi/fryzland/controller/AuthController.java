package com.zpi.fryzland.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController extends BaseController{
    @PostMapping("/employee/login")
    public String loginEmployee(){
        return "kappa";
    }
    @PostMapping("/employee/register")
    public ResponseEntity<String> registerEmployee(){
        return null;
    }
    @PostMapping("/customer/login")
    public ResponseEntity<String> loginCustomer(){
        return null;
    }
    @PostMapping("customer/register")
    public ResponseEntity<String> registerCustomer(){
        return null;
    }
}
