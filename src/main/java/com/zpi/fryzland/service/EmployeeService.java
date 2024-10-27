package com.zpi.fryzland.service;

import com.zpi.fryzland.model.AssignmentToSalonModel;
import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AssignmentToSalonService assigmentService;

    public Optional<EmployeeModel> getByEmployeeEmail(String employeeEmail){
        return employeeRepository.findByEmployeeEmail(employeeEmail);
    }

    public Optional<EmployeeModel> getEmployeeById(Integer id){
        return employeeRepository.findById(id);
    }

    //todo: Zaimplementować metodę getAllEmployees()
    public List<EmployeeModel> getAllEmployees(){
        throw new UnsupportedOperationException();
    }

    public EmployeeModel addEmployee(EmployeeModel employeeModel){
        return employeeRepository.save(employeeModel);
    }

    public void removeEmployeeById(int id){
        employeeRepository.deleteById(id);
    }

    public void removeEmployee(EmployeeModel employeeModel){
        employeeRepository.delete(employeeModel);
    }

    public List<EmployeeModel> getAllEmployeesBySalonIDAndDate(Integer salonID, Date date){
        List<EmployeeModel> listOfEmployees = new ArrayList<>();
        List<AssignmentToSalonModel> listOfAllAssignments = assigmentService.findAllAssignmentsBySalonIDAndAssignmentDate(salonID, date);
        for(AssignmentToSalonModel salonModel: listOfAllAssignments){
            listOfEmployees.add(salonModel.getEmployeeModel());
        }
        return listOfEmployees;
    }

    public List<EmployeeModel> getAllEmployeesByAssignment(List<AssignmentToSalonModel> listOfAssignments){
        return listOfAssignments.stream()
                .map(AssignmentToSalonModel::getEmployeeModel)
                .distinct()
                .toList();
    }
}
