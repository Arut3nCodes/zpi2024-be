package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.EmployeeModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<EmployeeModel, Integer> {
    Optional<EmployeeModel> findByEmployeeEmail(String employeeEmail);
}
