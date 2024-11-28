package com.zpi.fryzland.service;

import com.zpi.fryzland.dto.EmployeeDTO;
import com.zpi.fryzland.mapper.EmployeeMapper;
import com.zpi.fryzland.model.AssignmentToSalonModel;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.support.Repositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;

    public Optional<EmployeeModel> getByEmployeeEmail(String employeeEmail){
        return employeeRepository.findByEmployeeEmail(employeeEmail);
    }

    public Optional<EmployeeModel> getEmployeeById(Integer id){
        return employeeRepository.findById(id);
    }

    public List<EmployeeModel> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public EmployeeModel addEmployee(EmployeeModel employeeModel){
        return employeeRepository.save(employeeModel);
    }

    public void removeEmployeeById(int id){
        employeeRepository.deleteById(id);
    }

    public void removeEmployee(EmployeeModel employeeModel){
        employeeRepository.delete(employeeModel);
    }

    public List<EmployeeModel> getAllEmployeesByAssignment(List<AssignmentToSalonModel> listOfAssignments){
        return listOfAssignments.stream()
                .map(AssignmentToSalonModel::getEmployeeModel)
                .distinct()
                .toList();
    }

    public List<EmployeeDTO> getAllEmployeesByIds(List<Integer> employeeIds){
        return employeeMapper.allToDTO(employeeRepository.findAllById(employeeIds));
    }

    public boolean passwordChange(int employeeID, String password){
        Optional<EmployeeModel> optionalCustomerModel = getEmployeeById(employeeID);
        if(optionalCustomerModel.isPresent()){
            EmployeeModel employeeModel = optionalCustomerModel.get();
            employeeModel.setEncryptedEmployeePassword(
                    passwordEncoder.encode(password)
            );
            employeeRepository.save(employeeModel);
            return true;
        }
        else{
            return false;
        }
    }
}
