package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.authmodel.EmployeeRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRequestRepository extends JpaRepository<EmployeeRequestModel, String> {
    Optional<EmployeeRequestModel> findEmployeeRequestModelByEmployeeModel_EmployeeEmail(String employeeEmail);
}
