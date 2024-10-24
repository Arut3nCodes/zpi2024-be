package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.EmployeeQualificationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeQualificationsRepository extends CrudRepository<EmployeeQualificationModel, Integer> {
}
