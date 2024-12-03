package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.CustomerDTO;
import com.zpi.fryzland.dto.EmailDTO;
import com.zpi.fryzland.dto.EmployeeDTO;
import com.zpi.fryzland.mapper.EmployeeMapper;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.service.EmployeeService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
