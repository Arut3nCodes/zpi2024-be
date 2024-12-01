package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.AssignmentToSalonModel;
import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.model.SalonModel;
import jakarta.validation.constraints.FutureOrPresent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentToSalonRepository extends JpaRepository<AssignmentToSalonModel, Integer> {
    List<AssignmentToSalonModel> findAllBySalonModelAndAssignmentDate(SalonModel salonModel, LocalDate date);
    List<AssignmentToSalonModel> findAllBySalonModel (SalonModel salonModel);
    List<AssignmentToSalonModel> findAllBySalonModelAndEmployeeModel(SalonModel salonModel, EmployeeModel employeeModel);

    List<AssignmentToSalonModel> findAllBySalonModelAndEmployeeModelAndAssignmentDateBefore(SalonModel salonModel, EmployeeModel employeeModel, @FutureOrPresent LocalDate assignmentDate);
    Optional<AssignmentToSalonModel> findAssignmentToSalonModelBySalonModelAndEmployeeModelAndAssignmentDate(SalonModel salonModel, EmployeeModel employeeModel, @FutureOrPresent LocalDate assignmentDate);
}
