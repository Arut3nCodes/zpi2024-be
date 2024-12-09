package com.zpi.fryzland.service;

import com.zpi.fryzland.dto.CustomerDTO;
import com.zpi.fryzland.mapper.CustomerMapper;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.model.ServiceCategoryModel;
import com.zpi.fryzland.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    public Optional<CustomerModel> findByEmail(String customerEmail){
        return customerRepository.findByCustomerEmail(customerEmail);
    }

    public List<CustomerDTO> getAllByCustomerIds(List<Integer> listOfIds){
        return customerRepository.findAllById(listOfIds)
                .stream()
                .map(customerModel -> customerMapper.toDTO(customerModel))
                .toList();
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

    public void editCustomer(CustomerModel customerModel){
        customerRepository.save(customerModel);
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

    public List<CustomerModel> getAllCustomersWithSetServiceCategory(ServiceCategoryModel serviceCategoryModel){
        return customerRepository.findAllByServiceCategoryModel(serviceCategoryModel);
    }
}
