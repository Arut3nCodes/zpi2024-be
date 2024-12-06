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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/api/crud/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

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

    @PostMapping("/getAllById")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(@RequestBody List<Integer> listOfCustomerId){
        try{
            if(!listOfCustomerId.isEmpty()){
                List<CustomerDTO> listOfCustomers = customerService.getAllByCustomerIds(listOfCustomerId);
                if(listOfCustomers.size() == listOfCustomerId.size()){
                    return ResponseEntity.ok(listOfCustomers);
                }
            }
            return ResponseEntity.notFound().build();
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<String> updateCustomer(@RequestBody CustomerDTO customerDTO){
        try{
            Optional<CustomerModel> customerModel = customerService.findCustomerById(customerDTO.getCustomerID());
            if(customerModel.isPresent()){
                CustomerDTO dtoToUpdate = customerMapper.toDTO(customerModel.get());
                dtoToUpdate.setCustomerEmail(customerDTO.getCustomerEmail());
                dtoToUpdate.setCustomerName(customerDTO.getCustomerName());
                dtoToUpdate.setCustomerSurname(customerDTO.getCustomerSurname());
                dtoToUpdate.setCustomerDialNumber(customerDTO.getCustomerDialNumber());
                dtoToUpdate.setServiceCategoryID(customerDTO.getServiceCategoryID());
                customerService.editCustomer(customerMapper.toModel(dtoToUpdate, true));
                return ResponseEntity.ok().build();
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{customerID}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int customerID){
        try{
            Optional<CustomerModel> customerModel = customerService.findCustomerById(customerID);
            if(customerModel.isPresent()){
                return ResponseEntity.ok(customerMapper.toDTO(customerModel.get()));
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
