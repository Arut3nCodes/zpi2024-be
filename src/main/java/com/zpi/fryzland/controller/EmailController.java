package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.MessageDTO;
import com.zpi.fryzland.service.EmailService;
import jakarta.mail.Message;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Controller
@RequestMapping("/api/test-email")
public class EmailController {
    private EmailService emailService;
    @Autowired
    EmailController(EmailService emailService){
        this.emailService = emailService;
    }
    @GetMapping("/test-email/{email}")
    public ResponseEntity<MessageDTO> sendTestEmail(@PathVariable String email){
        try{
            emailService.sendTestEmail(email);
            return ResponseEntity.ok(new MessageDTO("email sent"));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageDTO(e.getMessage()));
        }
    }

    //GetMapping("/")
}
