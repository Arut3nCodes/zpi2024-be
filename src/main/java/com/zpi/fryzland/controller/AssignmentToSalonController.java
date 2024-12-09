package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.AssigmentToSalonDTO;
import com.zpi.fryzland.dto.EmployeeDTO;
import com.zpi.fryzland.mapper.AssignmentToSalonMapper;
import com.zpi.fryzland.model.AssignmentToSalonModel;
import com.zpi.fryzland.model.VisitModel;
import com.zpi.fryzland.service.AssignmentToSalonService;
import com.zpi.fryzland.service.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/crud/assignment-to-salon")
@AllArgsConstructor
public class AssignmentToSalonController {

    private final AssignmentToSalonService assignmentToSalonService;
    private final AssignmentToSalonMapper assignmentToSalonMapper;
    private final VisitService visitService;

    @PostMapping("")
    public ResponseEntity<AssigmentToSalonDTO> addAssignmentToSalon(@RequestBody AssigmentToSalonDTO assigmentToSalonDTO){
        try{
            AssignmentToSalonModel assignmentToSalonModel = assignmentToSalonMapper.toModel(assigmentToSalonDTO, false);
            assignmentToSalonModel = assignmentToSalonService.addAssignment(assignmentToSalonModel);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(assignmentToSalonMapper.toDTO(assignmentToSalonModel));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<AssigmentToSalonDTO>> getAllAssignments(){
        try{
            List<AssigmentToSalonDTO> listOfAssignments = assignmentToSalonService.getAllAssingments();
            if(!listOfAssignments.isEmpty()){
                return ResponseEntity.ok(listOfAssignments);
            }
            else{
                return ResponseEntity.noContent().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<String> updateAssignmentToSalon(@RequestBody AssigmentToSalonDTO assigmentToSalonDTO){
        try{
            Optional<AssignmentToSalonModel> assigmentToSalonModel = assignmentToSalonService.findAssignmentById(assigmentToSalonDTO.getAssigmentID());
            if(assigmentToSalonModel.isPresent()){
                AssigmentToSalonDTO dtoToUpdate = assignmentToSalonMapper.toDTO(assigmentToSalonModel.get());
                dtoToUpdate.setAssigmentDate(assigmentToSalonDTO.getAssigmentDate());
                dtoToUpdate.setSalonID(assigmentToSalonDTO.getSalonID());
                dtoToUpdate.setEmployeeID(assigmentToSalonDTO.getEmployeeID());
                assignmentToSalonService.editAssignment(assignmentToSalonMapper.toModel(dtoToUpdate, true));
                return ResponseEntity.ok().build();
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{assignmentID}")
    public ResponseEntity<String> deleteAssignmentToSalon(@PathVariable int assignmentID){
        try{
            Optional<AssignmentToSalonModel> assignmentToSalonModel = assignmentToSalonService.findAssignmentById(assignmentID);
            if(assignmentToSalonModel.isPresent()){
                List<VisitModel> listOfVisits = visitService.getAllVisitsByAssignmentToSalonID(assignmentID);
                if(!listOfVisits.isEmpty())
                    for(VisitModel visitModel : listOfVisits)
                        visitModel.setAssigmentModel(null);

                assignmentToSalonService.deleteAssignmentToSalonById(assignmentToSalonModel.get().getAssignmentID());
                return ResponseEntity.ok().build();
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
