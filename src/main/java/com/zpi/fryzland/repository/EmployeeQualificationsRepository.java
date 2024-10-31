package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.model.EmployeeQualificationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeQualificationsRepository extends CrudRepository<EmployeeQualificationModel, Integer> {
    List<EmployeeQualificationModel> getAllByEmployeeModel(EmployeeModel employeeModel);
}
