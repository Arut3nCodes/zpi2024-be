package com.zpi.fryzland.service;

import com.zpi.fryzland.dto.VisitWithIdsDTO;
import com.zpi.fryzland.model.VisitModel;
import com.zpi.fryzland.model.enums.VisitStatus;
import com.zpi.fryzland.repository.VisitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
//todo: Dodac znajdowanie z filtrem (zapytac sie o to na daily)
public class VisitService {
    private final VisitRepository repository;

    public VisitModel addVisit(VisitModel visitModel){
        return repository.save(visitModel);
    }

    public Optional<VisitModel> getVisitById(int id){
        return repository.findById(id);
    }

    public List<VisitModel> getAllVisitsByCustomerID(int customerID){
        return repository.getAllByCustomerModel_CustomerID(customerID);
    }

    public List<VisitModel> getAllVisitsAtDateByCustomerID(LocalDate date, int customerID){
        return repository.getAllByCustomerModel_CustomerIDAndVisitDate(customerID, date);
    }

    public List<VisitModel> getAllVisitsByEmployeeID(int employeeID){
        return repository.getAllByAssigmentModel_EmployeeModel_EmployeeID(employeeID);
    }

    public VisitModel updateModel(VisitModel visitModel){
        Optional<VisitModel> optionalVisitModel = getVisitById(visitModel.getVisitID());
        if(optionalVisitModel.isPresent()){
            return addVisit(optionalVisitModel.get());
        }
        return null;
    }

    public List<VisitModel> getAllVisits(){
        return repository.findAll();
    }

    public void deleteVisit(VisitModel visitModel){
        repository.delete(visitModel);
    }

    public void deleteVisitById(int id){
        repository.deleteById(id);
    }

    public List<VisitWithIdsDTO> getAllVisitsWithIdsForCustomer(int customerID){
        return getAllVisitsByCustomerID(customerID)
                .stream()
                .map(visitModel -> new VisitWithIdsDTO(visitModel))
                .toList();
    }

    public List<VisitWithIdsDTO> getAllVisitsWithIdsForEmployee(int employeeID){
        return getAllVisitsByEmployeeID(employeeID)
                .stream()
                .map(visitModel -> new VisitWithIdsDTO(visitModel))
                .toList();
    }

    public List<VisitModel> getAllVisitsByAssignmentToSalonID(int assignmentToSalonID){
        return repository.getAllByAssigmentModel_AssignmentID(assignmentToSalonID);
    }

    public Integer getNumberOfVisitsByCustomerID(int customerID){
        return repository.countAllByCustomerModel_CustomerIDAndVisitStatus(customerID, VisitStatus.DONE);
    }
}
