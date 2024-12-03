package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.ServicesIncludedInTheVisitModel;
import com.zpi.fryzland.model.VisitModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesIncludedInTheVisitRepository extends JpaRepository<ServicesIncludedInTheVisitModel, Integer> {
    List<ServicesIncludedInTheVisitModel> getAllByVisitModel_VisitID(int visitID);
    List<ServicesIncludedInTheVisitModel> getAllByVisitModel_CustomerModel_CustomerID(int customerID);
    List<ServicesIncludedInTheVisitModel> getAllByVisitModel_AssigmentModel_EmployeeModel_EmployeeID(int employeeID);
    List<ServicesIncludedInTheVisitModel> getAllByVisitModel(VisitModel visitModel);]
    List<ServicesIncludedInTheVisitModel> getAllByVisitModel_AssigmentModel_SalonModel_SalonID(int salonID);
}
