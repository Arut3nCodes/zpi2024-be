package com.zpi.fryzland.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    public enum EmailType{
        TEST_EMAIL,
        REGISTER,
        ACCOUNT_CONFIRMED,
        PASSWORD_LOST,
        PASSWORD_CHANGED,
        EMAIL_CHANGED,
        VISIT_RESERVED,
        VISIT_REMINDER,
        VISIT_CUSTOMER_CANCELLED,
        VISIT_EMPLOYEE_CANCELLED,
        VISIT_DATETIME_CHANGED,

    }

    public void sendEmail(String to, String subject, String template, Context context) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        String htmlContent = templateEngine.process(template, context);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        mailSender.send(mimeMessage);
    }

    public void sendTestEmail(String to) throws MessagingException {
        Context context = new Context();
        context.setVariable("email", to);
        sendEmail(to, "TEST", "test-email", context);
    }




}
