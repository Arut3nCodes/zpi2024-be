package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.EmailDTO;
import com.zpi.fryzland.dto.PasswordDTO;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.service.PasswordChangeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class PasswordChangeController {
    private final PasswordChangeService service;

    @PostMapping("api/crud/customer/password-change-request")
    public ResponseEntity<HttpStatus> requestCustomerPasswordChange(@RequestBody EmailDTO emailDTO){
        try{
            if(service.requestCustomerPasswordChange(emailDTO)){
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch(Exception e){
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("api/crud/employee/password-change-request")
    public ResponseEntity<HttpStatus> requestEmployeePasswordChange(@RequestBody EmailDTO emailDTO){
        try{
            if(service.requestEmployeePasswordChange(emailDTO)){
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("api/crud/customer/password-change/{requestID}")
    public ResponseEntity<HttpStatus> changeCustomerPassword(@PathVariable String requestID, @RequestBody PasswordDTO dto){
        try{
            if(service.changeCustomerPassword(requestID, dto.getPassword())){
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("api/crud/employee/password-change/{requestID}")
    public ResponseEntity<HttpStatus> changeEmployeePassword(@PathVariable String requestID, @RequestBody PasswordDTO dto){
        try{
            if(service.changeEmployeePassword(requestID, dto.getPassword())){
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
