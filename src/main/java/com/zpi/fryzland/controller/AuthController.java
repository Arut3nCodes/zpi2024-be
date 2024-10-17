package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.CustomerDTO;
import com.zpi.fryzland.dto.EmployeeDTO;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController extends BaseController{

    private final CustomerService customerService

    @Autowired
    public AuthController(CustomerService customerService){

    }

    @PostMapping("/employee/login")
    public String loginEmployee(){
        return "kappa";
    }
    @PostMapping("/employee/register")
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            if (employeeDTO == null) {
                return ResponseEntity.badRequest().body("Żądanie nie zawiera żadnego obiektu");
            } else if(true){
                throw new UnsupportedOperationException();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    @PostMapping("/customer/login")
    public ResponseEntity<String> loginCustomer(){
        return null;
    }
    @PostMapping("/customer/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerDTO customerDTO){
        try{
            if (customerDTO == null) {
                return ResponseEntity.badRequest().body("Żądanie nie zawiera żadnego obiektu");
            }
            Optional<CustomerModel> customerModelOptional = this.customerService.findByEmail(customerDTO.getCustomerEmail());
            if(customerModelOptional.isEmpty()){
                this.customerService.addCustomer(new CustomerModel(

                ))
            }
            else{
                return ResponseEntity.badRequest().body("Obiekt");
            }

        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
}

