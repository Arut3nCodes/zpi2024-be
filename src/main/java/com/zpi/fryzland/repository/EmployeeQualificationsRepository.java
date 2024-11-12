package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.model.EmployeeQualificationModel;
import com.zpi.fryzland.model.ServiceCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeQualificationsRepository extends JpaRepository<EmployeeQualificationModel, Integer> {
    List<EmployeeQualificationModel> getAllByEmployeeModel(EmployeeModel employeeModel);
    List<EmployeeQualificationModel> getAllByEmployeeModelInAndServiceCategoryModelIn(List<EmployeeModel> employeeModelList, List<ServiceCategoryModel> serviceCategoryModelList);
}
