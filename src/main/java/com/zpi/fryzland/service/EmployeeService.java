package com.zpi.fryzland.service;

import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public Optional<EmployeeModel> findByEmployeeEmail(String employeeEmail){
        return employeeRepository.findByEmployeeEmail(employeeEmail);
    }

    public EmployeeModel addEmployee(EmployeeModel employeeModel){
        return employeeRepository.save(employeeModel);
    }
}
