package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.VisitModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<VisitModel, Integer> {
    public Optional<VisitModel> getAllByVisitDateBefore(LocalDate beforeDate);
    public Optional<VisitModel> getAllByVisitDateAfter(LocalDate afterDate);
    public Optional<VisitModel> getAllByVisitDateBetween(LocalDate beforeDate, LocalDate afterDate);
    public List<VisitModel> getAllByCustomerModel_CustomerID(Integer customerID);
    public List<VisitModel> getAllByAssigmentModel_EmployeeModel_EmployeeID(Integer employeeID);
}
