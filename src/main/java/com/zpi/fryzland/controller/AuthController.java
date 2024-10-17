package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.CustomerDTO;
import com.zpi.fryzland.dto.EmployeeDTO;
import com.zpi.fryzland.mapper.CustomerMapper;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController extends BaseController{

    private final CustomerService customerService;

    @Autowired
    public AuthController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/employee/login")
    public ResponseEntity<String> loginEmployee(){
        try{
            throw new UnsupportedOperationException();
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    @PostMapping("/employee/register")
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            throw new UnsupportedOperationException();
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    @PostMapping("/customer/login")
    public ResponseEntity<String> loginCustomer(){
        try{
            throw new UnsupportedOperationException();
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    @PostMapping("/customer/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerDTO customerDTO){
        try{
            if (customerDTO == null) {
                return ResponseEntity.badRequest().body("Żądanie nie zawiera żadnego obiektu klienta");
            }
            Optional<CustomerModel> customerModelOptional = this.customerService.findByEmail(customerDTO.getCustomerEmail());
            if(customerModelOptional.isEmpty()){
                this.customerService.addCustomer(CustomerMapper.toModel(customerDTO));
                return ResponseEntity.ok("Poprawnie dodano klienta do bazy danych!");
            }
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Klient istnieje już w bazie danych");

        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
}

