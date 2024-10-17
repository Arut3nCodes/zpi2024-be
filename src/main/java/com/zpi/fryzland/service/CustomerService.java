package com.zpi.fryzland.service;

import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Optional<CustomerModel> findByEmail(String customerEmail){
        return customerRepository.findByEmail(customerEmail);
    }

    public CustomerModel addCustomer(CustomerModel customerModel){
        return customerRepository.save(customerModel);
    }

}
