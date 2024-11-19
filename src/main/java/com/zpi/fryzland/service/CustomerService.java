package com.zpi.fryzland.service;

import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<CustomerModel> findByEmail(String customerEmail){
        return customerRepository.findByCustomerEmail(customerEmail);
    }

    public Optional<CustomerModel> findCustomerById(int id){
        return customerRepository.findById(id);
    }

    public CustomerModel addCustomer(CustomerModel customerModel){
        return customerRepository.save(customerModel);
    }

    public void removeCustomerById(int id){
        customerRepository.deleteById(id);
    }

    public void removeCustomer(CustomerModel customerModel){
        customerRepository.delete(customerModel);
    }

    public void editCustomer(int id, CustomerModel customerModel){
        throw new UnsupportedOperationException();
    }

    public boolean passwordChange(int customerID, String password){
        Optional<CustomerModel> optionalCustomerModel = findCustomerById(customerID);
        if(optionalCustomerModel.isPresent()){
            CustomerModel customerModel = optionalCustomerModel.get();
            customerModel.setEncryptedCustomerPassword(
                    passwordEncoder.encode(password)
            );
            customerRepository.save(customerModel);
            return true;
        }
        else{
            return false;
        }
    }
}
