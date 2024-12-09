package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.CustomerDTO;
import com.zpi.fryzland.dto.EmailDTO;
import com.zpi.fryzland.dto.EmployeeDTO;
import com.zpi.fryzland.dto.RatingDTO;
import com.zpi.fryzland.mapper.EmployeeMapper;
import com.zpi.fryzland.model.*;
import com.zpi.fryzland.service.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("api/crud/employee")
public class EmployeeController {
    EmployeeService employeeService;
    EmployeeMapper employeeMapper;

    @PostMapping("/getAllByIds")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeesByListOfId(@RequestBody @NotNull @NotEmpty List<Integer> listOfIds){
        try{
            List<EmployeeDTO> listOfEmployees = employeeService.getAllEmployeesByIds(listOfIds);
            if(listOfEmployees.size() == listOfIds.size()){
                return ResponseEntity.ok(listOfEmployees);
            } else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/find-by-email")
    public ResponseEntity<EmployeeDTO> getUserByEmail(@RequestBody EmailDTO emailDTO){
        try{
            Optional<EmployeeModel> optionalEmployeeModel = employeeService.getByEmployeeEmail(emailDTO.getEmail());
            if (optionalEmployeeModel.isPresent()) {
                EmployeeDTO employeeDTO = employeeMapper.toDTO(optionalEmployeeModel.get());
                return ResponseEntity.ok(employeeDTO);
            }
            else{
                return ResponseEntity.notFound().build();
            }
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        try{
            List<EmployeeDTO> listOfEmployees = employeeMapper.allToDTO(employeeService.getAllEmployees());
            if(!listOfEmployees.isEmpty()){
                return ResponseEntity.ok(listOfEmployees);
            }
            else{
                return ResponseEntity.noContent().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{employeeID}")
    public ResponseEntity<EmployeeDTO> getEmployeeByID(@PathVariable int employeeID){
        try{
            Optional<EmployeeModel> employeeModel = employeeService.getEmployeeById(employeeID);
            if(employeeModel.isPresent()){
                return ResponseEntity.ok(employeeMapper.toDTO(employeeModel.get()));
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            Optional<EmployeeModel> employeeModel = employeeService.getEmployeeById(employeeDTO.getEmployeeID());
            if(employeeModel.isPresent()){
                EmployeeDTO dtoToUpdate = employeeMapper.toDTO(employeeModel.get());
                dtoToUpdate.setEmployeeName(employeeDTO.getEmployeeName());
                dtoToUpdate.setEmployeeSurname(employeeDTO.getEmployeeSurname());
                dtoToUpdate.setEmployeeDialNumber(employeeDTO.getEmployeeDialNumber());
                dtoToUpdate.setEmployeeEmail(employeeDTO.getEmployeeEmail());
                dtoToUpdate.setEmployeeBirthdayDate(employeeDTO.getEmployeeBirthdayDate());
                dtoToUpdate.setEmployeeEmploymentDate(employeeDTO.getEmployeeEmploymentDate());
                dtoToUpdate.setEmployeeMonthlyPay(employeeDTO.getEmployeeMonthlyPay());
                dtoToUpdate.setEmployeeCity(employeeDTO.getEmployeeCity());
                dtoToUpdate.setEmployeeStreet(employeeDTO.getEmployeeStreet());
                dtoToUpdate.setEmployeeBuildingNumber(employeeDTO.getEmployeeBuildingNumber());
                dtoToUpdate.setEmployeeApartmentNumber(employeeDTO.getEmployeeApartmentNumber());
                dtoToUpdate.setEmployeePostalCode(employeeDTO.getEmployeePostalCode());
                employeeService.updateEmployee(employeeMapper.toModel(dtoToUpdate, true));
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
