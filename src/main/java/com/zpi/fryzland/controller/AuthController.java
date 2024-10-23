package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.CustomerDTO;
import com.zpi.fryzland.dto.EmployeeDTO;
import com.zpi.fryzland.dto.LoginDTO;
import com.zpi.fryzland.dto.TokenDTO;
import com.zpi.fryzland.mapper.CustomerMapper;
import com.zpi.fryzland.mapper.EmployeeMapper;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.security.authentication.AuthService;
import com.zpi.fryzland.service.CustomerService;
import com.zpi.fryzland.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController extends BaseController{

    //todo: zmienić na 201 przy tworzeniu

    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/employee/login")
    public ResponseEntity<TokenDTO> loginEmployee(@RequestBody LoginDTO loginDTO){
        try{
            String token = authService.login(loginDTO,"USER_EMPLOYEE");
            return ResponseEntity.ok(new TokenDTO(token));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .build();
        }
    }
    @PostMapping("/employee/register")
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            if (employeeDTO == null) {
                return ResponseEntity
                        .badRequest()
                        .build();
            }
            Optional<EmployeeModel> employeeModelOptional = this.employeeService.findByEmployeeEmail(employeeDTO.getEmployeeEmail());
            if(employeeModelOptional.isEmpty()){
                employeeDTO.setEncryptedEmployeePassword(passwordEncoder.encode(employeeDTO.getEncryptedEmployeePassword()));
                this.employeeService.addEmployee(EmployeeMapper.toModel(employeeDTO));
                return ResponseEntity
                        .ok()
                        .build();
            }
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();

        }catch(Exception e){
            //todo: Usunąć potem dla bezpieczeństwa api ciało requesta
            return ResponseEntity
                    .badRequest()
                    .body(e.toString());
        }
    }
    @PostMapping("/customer/login")
    public ResponseEntity<TokenDTO> loginCustomer(@RequestBody LoginDTO loginDTO){
        try{
            String token = authService.login(loginDTO, "USER_CUSTOMER");
            return ResponseEntity
                    .ok(new TokenDTO(token));
        }catch(Exception e){
            //todo: Usunąć potem dla bezpieczeństwa api ciało requesta
            return ResponseEntity
                    .badRequest()
                    .build();
        }
    }
    @PostMapping("/customer/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerDTO customerDTO){
        try{
            if (customerDTO == null){
                return ResponseEntity
                        .badRequest()
                        .build();
            }
            Optional<CustomerModel> customerModelOptional = this.customerService.findByEmail(customerDTO.getCustomerEmail());
            if(customerModelOptional.isEmpty()){
                customerDTO.setEncryptedCustomerPassword(passwordEncoder.encode(customerDTO.getEncryptedCustomerPassword()));
                this.customerService.addCustomer(CustomerMapper.toModel(customerDTO));
                return ResponseEntity
                        .ok()
                        .build();
            }
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .build();

        }catch(Exception e){
            //todo: Usunąć potem dla bezpieczeństwa api ciało requesta
            return ResponseEntity
                    .badRequest()
                    .body(e.toString());
        }
    }
}

