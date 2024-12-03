package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.VisitModel;
import jakarta.validation.constraints.FutureOrPresent;
import com.zpi.fryzland.model.enums.VisitStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<VisitModel, Integer> {
    public Optional<VisitModel> getAllByVisitDateBefore(LocalDate beforeDate);
    public Optional<VisitModel> getAllByVisitDateAfter(LocalDate afterDate);
    public Optional<VisitModel> getAllByVisitDateBetween(LocalDate beforeDate, LocalDate afterDate);
    public List<VisitModel> getAllByCustomerModel_CustomerID(Integer customerID);
    public List<VisitModel> getAllByAssigmentModel_EmployeeModel_EmployeeID(Integer employeeID);
    public List<VisitModel> getAllByCustomerModel_CustomerIDAndVisitDate(Integer customerModel_customerID, LocalDate visitDate);
    public List<VisitModel> getAllByAssigmentModel_AssignmentID(Integer assignmentID);
    public Integer countAllByCustomerModel_CustomerIDAndVisitStatus(Integer customerID, VisitStatus visitStatus);
}
