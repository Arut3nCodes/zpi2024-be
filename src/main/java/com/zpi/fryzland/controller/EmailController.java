package com.zpi.fryzland.controller;

import com.zpi.fryzland.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Controller
@AllArgsConstructor
@RequestMapping("/api/email")
public class EmailController {
    private EmailService emailService;

    @GetMapping("/testEmail/{email}")
    public ResponseEntity<String> sendTestEmail(@PathVariable String email){
        try{
            emailService.sendEmail(email, "TEST", EmailService.EmailType.TEST_EMAIL);
            return ResponseEntity.ok("Mail sent");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
}
