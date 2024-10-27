package com.zpi.fryzland.service;

import com.zpi.fryzland.model.AssignmentToSalonModel;
import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.model.SalonModel;
import com.zpi.fryzland.repository.AssignmentToSalonRepository;
import com.zpi.fryzland.repository.EmployeeRepository;
import com.zpi.fryzland.repository.SalonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AssignmentToSalonService {
    private final AssignmentToSalonRepository assigmentRepository;
    private final SalonService salonService;

    public AssignmentToSalonModel addAssignment(AssignmentToSalonModel assigmentModel){
        return assigmentRepository.save(assigmentModel);
    }

    public Optional<AssignmentToSalonModel> findAssignmentById(Integer id){
        return assigmentRepository.findById(id);
    }

    public List<AssignmentToSalonModel> findAllAssignments(){
        List<AssignmentToSalonModel> allAssignments = new ArrayList<>();
        for(AssignmentToSalonModel assigment: assigmentRepository.findAll()){
            allAssignments.add(assigment);
        }
        return allAssignments;
    }

    public List<AssignmentToSalonModel> findAllAssignmentsBySalonIDAndAssignmentDate(int salonID, Date date){
        List<AssignmentToSalonModel> allAssignments = new ArrayList<>();
        Optional<SalonModel> salonModel = salonService.getSalonById(salonID);
        if(salonModel.isPresent()){
            allAssignments.addAll(assigmentRepository.findAllBySalonModelAndAssignmentDate(salonModel.get(), date));
            return allAssignments;
        }
        return null;
    }

    public List<AssignmentToSalonModel> findAllAssignmentsBySalonID(int salonID){
        List<AssignmentToSalonModel> allAssignments = new ArrayList<>();
        Optional<SalonModel> salonModel = salonService.getSalonById(salonID);
        if(salonModel.isPresent()){
            allAssignments.addAll(assigmentRepository.findAllBySalonModel(salonModel.get()));
            return allAssignments;
        }
        return null;
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
