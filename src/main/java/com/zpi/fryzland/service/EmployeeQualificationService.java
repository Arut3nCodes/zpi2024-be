package com.zpi.fryzland.service;

import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.model.EmployeeQualificationModel;
import com.zpi.fryzland.repository.EmployeeQualificationsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeQualificationService {
    private final EmployeeQualificationsRepository qualificationsRepository;

    public List<EmployeeQualificationModel> findAllQualificationsByEmployee(List<EmployeeModel> employeeModelList){
        List<EmployeeQualificationModel> qualificationsList = new ArrayList<>();
        for(EmployeeModel employeeModel : employeeModelList){
            qualificationsList.addAll(qualificationsRepository.getAllByEmployeeModel(employeeModel));
        }
        return qualificationsList;
    }

}
