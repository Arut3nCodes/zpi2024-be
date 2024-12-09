package com.zpi.fryzland.service;

import com.zpi.fryzland.model.authmodel.CustomerRequestModel;
import com.zpi.fryzland.model.authmodel.EmployeeRequestModel;
import com.zpi.fryzland.repository.CustomerRequestRepository;
import com.zpi.fryzland.repository.EmployeeRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeRequestService {
    private final EmployeeRequestRepository requestRepository;

    Optional<EmployeeRequestModel> getRequestById(String id){
        return requestRepository.findById(id);
    }

    Optional<EmployeeRequestModel> getRequestByEmployeeEmail(String email){
        return requestRepository.findEmployeeRequestModelByEmployeeModel_EmployeeEmail(email);
    }

    EmployeeRequestModel addRequest(EmployeeRequestModel requestModel){
        return requestRepository.save(requestModel);
    }

    public void deleteRequest(EmployeeRequestModel requestModel){
        requestRepository.delete(requestModel);
    }
}
