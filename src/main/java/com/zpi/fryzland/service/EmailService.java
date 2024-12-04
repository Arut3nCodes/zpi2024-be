package com.zpi.fryzland.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {
    private JavaMailSender mailSender;
    public enum EmailType{
        TEST_EMAIL,
        REGISTER,
        PASSWORD_LOST,
        PASSWORD_CHANGED,
        VISIT_RESERVED,
        VISIT_REMINDER,
        VISIT_CUSTOMER_CANCELLED,
        VISIT_EMPLOYEE_CANCELLED,
        VISIT_DATE_OR_TIME_CHANGED,
    }

    public void sendEmail(String to, String subject, EmailType emailType) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        String messageText;
        if(emailType == EmailType.TEST_EMAIL){
            messageText = "TEST_MAIL";
        } else{
           messageText = "doesn't work buddy";
        }
        message.setText(messageText);
        mailSender.send(message);
    }
}
