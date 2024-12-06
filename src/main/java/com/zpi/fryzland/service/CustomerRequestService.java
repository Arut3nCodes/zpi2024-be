package com.zpi.fryzland.service;

import com.zpi.fryzland.model.authmodel.CustomerRequestModel;
import com.zpi.fryzland.repository.CustomerRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerRequestService {

    private final CustomerRequestRepository requestRepository;

    Optional<CustomerRequestModel> getRequestById(String id){
        return requestRepository.findById(id);
    }

    Optional<CustomerRequestModel> getRequestByCustomerEmail(String customerEmail){
        return requestRepository.findCustomerRequestModelByCustomerModel_CustomerEmail(customerEmail);
    }

    CustomerRequestModel addRequest(CustomerRequestModel requestModel){
        return requestRepository.save(requestModel);
    }

    public void deleteRequest(CustomerRequestModel requestModel){
        requestRepository.delete(requestModel);
    }
}
