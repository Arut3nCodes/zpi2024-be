package com.zpi.fryzland.service;

import com.zpi.fryzland.dto.EmailDTO;
import com.zpi.fryzland.dto.PasswordDTO;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.model.authmodel.CustomerRequestModel;
import com.zpi.fryzland.model.authmodel.EmployeeRequestModel;
import com.zpi.fryzland.model.enums.RequestType;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PasswordChangeService{
    private final CustomerService customerService;
    private final CustomerRequestService customerRequestService;
    private final EmployeeService employeeService;
    private final EmployeeRequestService employeeRequestService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    public boolean requestCustomerPasswordChange(EmailDTO emailDTO) {
        try{
            String emailAddress = emailDTO.getEmail();
            Optional<CustomerModel> optionalEmployeeModel = customerService.findByEmail(emailAddress);
            Optional<CustomerRequestModel> optionalCustomerRequestModel = customerRequestService.getRequestByCustomerEmail(emailAddress);
            if(optionalEmployeeModel.isPresent() && optionalCustomerRequestModel.isEmpty()){
                    System.out.println("przeszlo");
                    CustomerModel customerModel = optionalEmployeeModel.get();

                    LocalDateTime deadline = LocalDateTime.now().plusDays(1);

                    CustomerRequestModel requestModel = new CustomerRequestModel(
                            null,
                            RequestType.PASSWORD_CHANGE,
                            deadline.toLocalDate(),
                            deadline.toLocalTime(),
                            customerModel);

                    requestModel = customerRequestService.addRequest(requestModel);
                    emailService.sendPasswordChangeRequestEmail(emailAddress, requestModel.getCustomerRequestId(), "customer");
                    return true;

            }
            return false;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean changeCustomerPassword(String requestId, String password){
        try{
            Optional<CustomerRequestModel> customerRequestModel = customerRequestService.getRequestById(requestId);
            if (customerRequestModel.isPresent() && (password.length() >= 8 && password.length() <= 24)) {
                CustomerModel customerModel = customerRequestModel.get().getCustomerModel();
                customerModel.setEncryptedCustomerPassword(passwordEncoder.encode(password));
                //todo: na employment-development podmienic na update !!!!!
                customerModel = customerService.addCustomer(customerModel);
                if(customerModel != null){
                    emailService.sendPasswordChangedEmail(customerModel.getCustomerEmail());
                    return true;
                }
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }

    public boolean requestEmployeePasswordChange(EmailDTO emailDTO) {
        try{
            String emailAddress = emailDTO.getEmail();
            Optional<EmployeeModel> optionalEmployeeModel = employeeService.findByEmployeeEmail(emailAddress);
            Optional<EmployeeRequestModel> optionalEmployeeRequestModel = employeeRequestService.getRequestByEmployeeEmail(emailAddress);
            if(optionalEmployeeModel.isPresent() && optionalEmployeeRequestModel.isEmpty()){

                EmployeeModel employeeModel = optionalEmployeeModel.get();

                LocalDateTime deadline = LocalDateTime.now().plusDays(1);

                EmployeeRequestModel requestModel = new EmployeeRequestModel(
                        null,
                        RequestType.PASSWORD_CHANGE,
                        deadline.toLocalDate(),
                        deadline.toLocalTime(),
                        employeeModel);

                requestModel = employeeRequestService.addRequest(requestModel);
                emailService.sendPasswordChangeRequestEmail(emailAddress, requestModel.getEmployeeRequestId(), "employee");
                return true;

            }
            return false;
        } catch(Exception e){
            return false;
        }
    }

    public boolean changeEmployeePassword(String requestId, String password){
        try{
            Optional<EmployeeRequestModel> employeeRequestModel = employeeRequestService.getRequestById(requestId);
            if (employeeRequestModel.isPresent() && (password.length() >= 8 && password.length() <= 24)) {
                EmployeeModel employeeModel = employeeRequestModel.get().getEmployeeModel();
                employeeModel.setEncryptedEmployeePassword(passwordEncoder.encode(password));
                //todo: na employment-development podmienic na update !!!!!
                employeeModel = employeeService.addEmployee(employeeModel);
                if(employeeModel != null){
                    emailService.sendPasswordChangedEmail(employeeModel.getEmployeeEmail());
                    return true;
                }
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
}