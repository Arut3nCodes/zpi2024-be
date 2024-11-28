package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.EmployeeDTO;
import com.zpi.fryzland.dto.EmployeeQualificationDTO;
import com.zpi.fryzland.mapper.EmployeeQualificationMapper;
import com.zpi.fryzland.model.EmployeeQualificationModel;
import com.zpi.fryzland.service.EmployeeQualificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/crud/employee-qualification")
@AllArgsConstructor
public class EmployeeQualificationController {

    private final EmployeeQualificationService employeeQualificationService;
    private final EmployeeQualificationMapper employeeQualificationMapper;

    @PostMapping("")
    public ResponseEntity<EmployeeQualificationDTO> addEmployeeQualification(@RequestBody EmployeeQualificationDTO employeeQualificationDTO){
        try{
            EmployeeQualificationModel employeeQualificationModel = employeeQualificationMapper.toModel(employeeQualificationDTO, false);
            employeeQualificationModel = employeeQualificationService.addEmployeeQualification(employeeQualificationModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeQualificationMapper.toDTO(employeeQualificationModel));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<EmployeeQualificationDTO>> getAllEmployeeQualifications(){
        try{
            List<EmployeeQualificationDTO> listOfEmployeeQualifications = employeeQualificationMapper.allToDTO(employeeQualificationService.getAllEmployeeQualifications());
            if(!listOfEmployeeQualifications.isEmpty()){
                return ResponseEntity.ok(listOfEmployeeQualifications);
            }
            else{
                return ResponseEntity.noContent().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<String> updateEmployeeQualification(@RequestBody EmployeeQualificationDTO employeeQualificationDTO){
        try{
            Optional<EmployeeQualificationModel> employeeQualificationModel = employeeQualificationService.getEmployeeQualificationById(employeeQualificationDTO.getEmployeeQualificationID());
            if(employeeQualificationModel.isPresent()){
                EmployeeQualificationDTO dtoToUpdate = employeeQualificationMapper.toDTO(employeeQualificationModel.get());
                dtoToUpdate.setServiceCategoryID(employeeQualificationDTO.getServiceCategoryID());
                dtoToUpdate.setEmployeeID(employeeQualificationDTO.getEmployeeID());
                dtoToUpdate.setServiceCategoryID(employeeQualificationDTO.getServiceCategoryID());
                employeeQualificationService.updateEmployeeQualifications(employeeQualificationMapper.toModel(dtoToUpdate, true));
                return ResponseEntity.ok().build();
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeQualification(@PathVariable int id){
        try{
            Optional<EmployeeQualificationModel> model = employeeQualificationService.getEmployeeQualificationById(id);
            if(model.isPresent()){
                employeeQualificationService.removeEmployeeQualification(model.get());
                return ResponseEntity.ok().build();
            }
            else{
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
