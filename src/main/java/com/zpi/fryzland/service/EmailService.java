package com.zpi.fryzland.service;

import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.model.EmployeeModel;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.time.LocalDate;

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

    void basicEmailContextMapping(Context context){
        context.setVariable("company", "Ateliers De Style");
        context.setVariable("year", LocalDate.now().getYear());
        context.setVariable("email", System.getenv("email_username"));
    }

    void basicHelperMapping(MimeMessageHelper helper) throws MessagingException {
        File logo = new File("src/main/resources/static/logo_name.png");
        File barber_image = new File("src/main/resources/static/vintage_barber_shop.jpg");
        helper.addInline("logo", logo);
        helper.addInline("barber_image", barber_image);
    }

    public void sendEmail(String to, String subject, String template, Context context) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        String htmlContent = templateEngine.process(template, context);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        basicHelperMapping(helper);
        mailSender.send(mimeMessage);
    }

    public void sendTestEmail(String to) throws MessagingException {
        Context context = new Context();
        basicEmailContextMapping(context);
        context.setVariable("email", to);
        sendEmail(to, "TEST", "test-email", context);
    }

    public void sendRegisteredCustomerEmail(String to, CustomerModel customerModel) throws MessagingException{
        Context context = new Context();
        basicEmailContextMapping(context);
        context.setVariable("firstName", customerModel.getCustomerName());
        context.setVariable("lastName", customerModel.getCustomerSurname());
        sendEmail(to, "Pomyślna rejestracja w serwisie Ateliers De Style", "registered-customer-email", context);
    }

    public void sendRegisteredEmployeeEmail(String to, EmployeeModel employeeModel) throws MessagingException{
        Context context = new Context();
        basicEmailContextMapping(context);
        context.setVariable("firstName", employeeModel.getEmployeeName());
        context.setVariable("lastName", employeeModel.getEmployeeSurname());
        sendEmail(to, "Witaj w gronie fryzjerów Ateliers De Style", "registered-employee-email", context);
    }

    //public void sendRegistered

}
