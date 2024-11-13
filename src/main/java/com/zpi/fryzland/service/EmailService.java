package com.zpi.fryzland.service;

import com.zpi.fryzland.controller.MailConfigChecker;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender mailSender;
    private MailConfigChecker mailConfigChecker;
    public enum EmailType{
        TEST_EMAIL,
        REGISTER,
        PASSWORD_LOST,
        VISIT_RESERVED,
        VISIT_REMINDER,
        VISIT_CUSTOMER_CANCELLED,
        VISIT_EMPLOYEE_CANCELLED,
        VISIT_CHANGED
    }

    public void sendEmail(String to, String subject, EmailType emailType) {
        mailConfigChecker.logMailConfig();
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
