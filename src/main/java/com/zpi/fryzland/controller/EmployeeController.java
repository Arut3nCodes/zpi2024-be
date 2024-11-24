package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.EmployeeDTO;
import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.service.EmployeeService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("api/crud/employee")
public class EmployeeController {
    EmployeeService employeeService;

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
}
