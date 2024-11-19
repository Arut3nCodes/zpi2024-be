package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.CustomerDTO;
import com.zpi.fryzland.dto.EmailDTO;
import com.zpi.fryzland.mapper.CustomerMapper;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.service.CustomerService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/api/crud/customer")
public class CustomerController {
    CustomerService customerService;
    CustomerMapper customerMapper;
    @GetMapping("/find-by-email")
    public ResponseEntity<CustomerDTO> getUserByEmail(@RequestBody EmailDTO emailDTO){
        try{
            Optional<CustomerModel> optionalCustomerModel = customerService.findByEmail(emailDTO.getEmail());
            if (optionalCustomerModel.isPresent()) {
                CustomerDTO customerDTO = customerMapper.toDTO(optionalCustomerModel.get());
                return ResponseEntity.ok(customerDTO);
            }
            else{
                return ResponseEntity.notFound().build();
            }
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
