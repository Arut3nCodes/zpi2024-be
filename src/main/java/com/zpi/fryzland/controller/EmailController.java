package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.MessageDTO;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.service.CustomerService;
import com.zpi.fryzland.service.EmailService;
import com.zpi.fryzland.service.EmployeeService;
import jakarta.mail.Message;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Optional;


@Controller
@RequestMapping("/api/test-email")
@AllArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    @PostMapping("/test-email/{email}")
    public ResponseEntity<MessageDTO> sendTestEmail(@PathVariable String email){
        try{
            emailService.sendTestEmail(email);
            return ResponseEntity.ok(new MessageDTO("email sent"));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageDTO(e.getMessage()));
        }
    }

    @PostMapping("/email-changed-email/{email}")
    public ResponseEntity<MessageDTO> sendEmailChangedEmail(@PathVariable String email){
        try{
            emailService.sendTestEmail(email);
            return ResponseEntity.ok(new MessageDTO("email sent"));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageDTO(e.getMessage()));
        }
    }

    @PostMapping("/password-changed-email/{email}")
    public ResponseEntity<MessageDTO> sendPasswordChangedEmail(@PathVariable String email){
        try{
            emailService.sendTestEmail(email);
            return ResponseEntity.ok(new MessageDTO("email sent"));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageDTO(e.getMessage()));
        }
    }

    @PostMapping("/password-lost-email/{email}")
    public ResponseEntity<MessageDTO> sendPasswordLostEmail(@PathVariable String email){
        try{
            emailService.sendTestEmail(email);
            return ResponseEntity.ok(new MessageDTO("email sent"));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageDTO(e.getMessage()));
        }
    }

    @PostMapping("/registered-customer-email/{email}/{customerId}")
    public ResponseEntity<MessageDTO> sendRegisteredCustomerEmail(@PathVariable String email, @PathVariable int customerId){
        try{
            Optional<CustomerModel> customerModel = customerService.findCustomerById(customerId);
            if(customerModel.isPresent()){
                emailService.sendRegisteredCustomerEmail(email, customerModel.get());
                return ResponseEntity.ok(new MessageDTO("email sent"));
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageDTO(e.getMessage()));
        }
    }

    @PostMapping("/registered-employee-email/{email}/{employeeId}")
    public ResponseEntity<MessageDTO> sendRegisteredEmployeeEmail(@PathVariable String email, @PathVariable int employeeId){
        try{
            Optional<EmployeeModel> employeeModel = employeeService.getEmployeeById(employeeId);
            if(employeeModel.isPresent()){
                emailService.sendRegisteredEmployeeEmail(email, employeeModel.get());
                return ResponseEntity.ok(new MessageDTO("email sent"));
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageDTO(e.getMessage()));
        }
    }


    @PostMapping("/visit-customer-cancelled-email/{email}")
    public ResponseEntity<MessageDTO> sendVisitCustomerCancelledEmail(@PathVariable String email){
        try{
            emailService.sendTestEmail(email);
            return ResponseEntity.ok(new MessageDTO("email sent"));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageDTO(e.getMessage()));
        }
    }

    @PostMapping("/visit-employee-cancelled-email/{email}")
    public ResponseEntity<MessageDTO> sendVisitEmployeeCancelledEmail(@PathVariable String email){
        try{
            emailService.sendTestEmail(email);
            return ResponseEntity.ok(new MessageDTO("email sent"));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageDTO(e.getMessage()));
        }
    }

    @PostMapping("/visit-datetime-changed-email/{email}")
    public ResponseEntity<MessageDTO> sendVisitDatetimeChangedEmail(@PathVariable String email){
        try{
            emailService.sendTestEmail(email);
            return ResponseEntity.ok(new MessageDTO("email sent"));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageDTO(e.getMessage()));
        }
    }

    @PostMapping("/visit-reminder-email/{email}")
    public ResponseEntity<MessageDTO> sendVisitRemainderEmail(@PathVariable String email){
        try{
            emailService.sendTestEmail(email);
            return ResponseEntity.ok(new MessageDTO("email sent"));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageDTO(e.getMessage()));
        }
    }

    @PostMapping("/visit-reserved-email/{email}/{visitId}")
    public ResponseEntity<MessageDTO> sendVisitReservedEmail(@PathVariable String email, @PathVariable int visitId){
        try{
            emailService.sendTestEmail(email);
            return ResponseEntity.ok(new MessageDTO("email sent"));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageDTO(e.getMessage()));
        }
    }

}
