package com.zpi.fryzland.service;

import com.zpi.fryzland.dto.EmployeeQualificationDTO;
import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.model.EmployeeQualificationModel;
import com.zpi.fryzland.model.ServiceCategoryModel;
import com.zpi.fryzland.repository.EmployeeQualificationsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeQualificationService {
    private final EmployeeQualificationsRepository qualificationsRepository;

    public EmployeeQualificationModel addEmployeeQualification(EmployeeQualificationModel employeeQualificationModel){
        return qualificationsRepository.save(employeeQualificationModel);
    }

    public Optional<EmployeeQualificationModel> getEmployeeQualificationById(int id){
        return qualificationsRepository.findById(id);
    }

    public void removeEmployeeQualificationById(int id){
        qualificationsRepository.deleteById(id);
    }

    public void removeEmployeeQualification(EmployeeQualificationModel employeeQualificationModel){
        qualificationsRepository.delete(employeeQualificationModel);
    }

    public List<EmployeeQualificationModel> findAllQualificationsByEmployee(List<EmployeeModel> employeeModelList){
        List<EmployeeQualificationModel> qualificationsList = new ArrayList<>();
        for(EmployeeModel employeeModel : employeeModelList){
            qualificationsList.addAll(qualificationsRepository.getAllByEmployeeModel(employeeModel));
        }
        return qualificationsList;
    }

    public List<EmployeeQualificationModel> findAllByEmployeesAndCategories(List<EmployeeModel> employeeModelList, List<ServiceCategoryModel> serviceCategoryModelList){
        return qualificationsRepository.getAllByEmployeeModelInAndServiceCategoryModelIn(employeeModelList, serviceCategoryModelList);
    }

    public List<EmployeeQualificationModel> getAllQualificationsByServiceCategoryID(int serviceCategoryID){
        return qualificationsRepository.getAllByServiceCategoryModel_ServiceCategoryId(serviceCategoryID);
    }

    public void updateEmployeeQualifications(EmployeeQualificationModel employeeQualificationModel){
        qualificationsRepository.save(employeeQualificationModel);
    }

    public List<EmployeeQualificationModel> getAllEmployeeQualifications(){
        return qualificationsRepository.findAll();
    }

}
