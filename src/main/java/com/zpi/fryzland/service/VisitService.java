package com.zpi.fryzland.service;

import com.zpi.fryzland.model.VisitModel;
import com.zpi.fryzland.repository.VisitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<VisitModel> getAllVisitsByEmployeeID(int employeeID){
        return repository.getAllByAssigmentModel_EmployeeModel_EmployeeID(employeeID);
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

}
