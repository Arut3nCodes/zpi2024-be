package com.zpi.fryzland.service;

import com.zpi.fryzland.dto.SaveVisitDTO;
import com.zpi.fryzland.model.AssignmentToSalonModel;
import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.model.SalonModel;
import com.zpi.fryzland.model.VisitModel;
import com.zpi.fryzland.repository.AssignmentToSalonRepository;
import com.zpi.fryzland.repository.EmployeeRepository;
import com.zpi.fryzland.repository.SalonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AssignmentToSalonService {
    private final AssignmentToSalonRepository assigmentRepository;
    private final SalonService salonService;
    private final EmployeeService employeeService;

    public AssignmentToSalonModel addAssignment(AssignmentToSalonModel assigmentModel) {
        return assigmentRepository.save(assigmentModel);
    }

    public Optional<AssignmentToSalonModel> findAssignmentById(Integer id) {
        return assigmentRepository.findById(id);
    }

    public List<AssignmentToSalonModel> findAllAssignments() {
        List<AssignmentToSalonModel> allAssignments = new ArrayList<>();
        for (AssignmentToSalonModel assigment : assigmentRepository.findAll()) {
            allAssignments.add(assigment);
        }
        return allAssignments;
    }

    public List<AssignmentToSalonModel> findAllAssignmentsBySalonIDAndAssignmentDate(int salonID, LocalDate date) {
        List<AssignmentToSalonModel> allAssignments = new ArrayList<>();
        Optional<SalonModel> salonModel = salonService.getSalonById(salonID);
        if (salonModel.isPresent()) {
            allAssignments.addAll(assigmentRepository.findAllBySalonModelAndAssignmentDate(salonModel.get(), date));
            return allAssignments;
        }
        return null;
    }

    public List<EmployeeModel> getAllEmployeesBySalonIDAndDate(Integer salonID, LocalDate date){
        List<EmployeeModel> listOfEmployees = new ArrayList<>();
        List<AssignmentToSalonModel> listOfAllAssignments = findAllAssignmentsBySalonIDAndAssignmentDate(salonID, date);
        for(AssignmentToSalonModel salonModel: listOfAllAssignments){
            listOfEmployees.add(salonModel.getEmployeeModel());
        }
        return listOfEmployees;
    }

    public AssignmentToSalonModel findAssignmentByEmployeeAndSalonAndDate(int salonID, int employeeID, LocalDate assignmentDate) {
        Optional<SalonModel> salonModel = salonService.getSalonById(salonID);
        Optional<EmployeeModel> employeeModel = employeeService.getEmployeeById(employeeID);
        if(salonModel.isPresent() && employeeModel.isPresent()){
            return assigmentRepository.findAssignmentToSalonModelBySalonModelAndEmployeeModelAndAssignmentDate(salonModel.get(), employeeModel.get(), assignmentDate).orElse(null);
        }
        else{
            return null;
        }
    }

    public List<AssignmentToSalonModel> findAllAssignmentsBySalonID(int salonID){
        List<AssignmentToSalonModel> allAssignments = new ArrayList<>();
        Optional<SalonModel> salonModel = salonService.getSalonById(salonID);
        if(salonModel.isPresent()){
            allAssignments.addAll(assigmentRepository.findAllBySalonModel(salonModel.get()));
            return allAssignments;
        }
        return allAssignments;
    }

    public List<AssignmentToSalonModel> findAllAssignmentsForSalonAndEmployeeBeforeDate(SalonModel salonModel, EmployeeModel employeeModel, LocalDate date){
        return assigmentRepository.findAllBySalonModelAndEmployeeModelAndAssignmentDateBefore(salonModel, employeeModel, date);
    }

    //todo: Wyciągnąć datę na zewnątrz żeby nie była hardcodowana w funkcji
    public List<LocalDate> getAllAvailabilityDatesForAnEmployee(int salonId, int employeeId){
        Optional<EmployeeModel> optionalEmployeeModel = employeeService.getEmployeeById(employeeId);
        Optional<SalonModel> optionalSalonModel = salonService.getSalonById(salonId);
        if(optionalEmployeeModel.isPresent() && optionalSalonModel.isPresent()){
            return assigmentRepository.findAllBySalonModelAndEmployeeModel(optionalSalonModel.get(), optionalEmployeeModel.get())
                    .stream()
                    .map(model -> model.getAssignmentDate())
                    .distinct()
                    .filter(date -> date.isBefore(LocalDate.now().plusDays(14)))
                    .toList();
        }
        return new ArrayList<>();
    }

    public void deleteAssignment(AssignmentToSalonModel assignmentModel){
        assigmentRepository.delete(assignmentModel);
    }

    public void deleteAssignmentToSalonById(Integer id){
        assigmentRepository.deleteById(id);
    }

    //todo: editAssignment() method
    public void editAssignment(){
        throw new UnsupportedOperationException();
    }
}
