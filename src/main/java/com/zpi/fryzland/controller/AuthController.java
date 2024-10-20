package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.CustomerDTO;
import com.zpi.fryzland.dto.EmployeeDTO;
import com.zpi.fryzland.dto.LoginDTO;
import com.zpi.fryzland.mapper.CustomerMapper;
import com.zpi.fryzland.mapper.EmployeeMapper;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.security.authentication.AuthService;
import com.zpi.fryzland.security.authentication.CustomUserDetailsService;
import com.zpi.fryzland.service.CustomerService;
import com.zpi.fryzland.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class AuthController extends BaseController{

    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/employee/login")
    public ResponseEntity<String> loginEmployee(@RequestBody LoginDTO loginDTO){
        try{
            String token = authService.login(new LoginDTO(),"USER_EMPLOYEE");
            return ResponseEntity.ok(token);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    @PostMapping("/employee/register")
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            if (employeeDTO == null) {
                return ResponseEntity.badRequest().body("Żądanie nie zawiera żadnego obiektu klienta");
            }
            Optional<CustomerModel> customerModelOptional = this.customerService.findByEmail(employeeDTO.getEmployeeEmail());
            if(customerModelOptional.isEmpty()){
                employeeDTO.setEncryptedEmployeePassword(passwordEncoder.encode(employeeDTO.getEncryptedEmployeePassword()));
                this.employeeService.addEmployee(EmployeeMapper.toModel(employeeDTO));
                return ResponseEntity.ok("Poprawnie dodano pracownika do bazy danych!");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Pracownik istnieje już w bazie danych");

        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    @PostMapping("/customer/login")
    public ResponseEntity<String> loginCustomer(@RequestBody LoginDTO loginDTO){
        try{
            String token = authService.login(loginDTO, "USER_CUSTOMER");
            return ResponseEntity.ok(token);
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
                customerDTO.setEncryptedCustomerPassword(passwordEncoder.encode(customerDTO.getEncryptedCustomerPassword()));
                this.customerService.addCustomer(CustomerMapper.toModel(customerDTO));
                return ResponseEntity.ok("Poprawnie dodano klienta do bazy danych!");
            }
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Klient istnieje już w bazie danych");

        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
}

