package com.zpi.fryzland.service;

import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Optional<CustomerModel> findByEmail(String customerEmail){
        return customerRepository.findByCustomerEmail(customerEmail);
    }

    public CustomerModel findCustomerById(int id){
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer was not found"));
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
}
