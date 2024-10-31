package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.SalonDTO;
import com.zpi.fryzland.dto.serviceDisplay.CategoryWithServicesDTO;
import com.zpi.fryzland.mapper.CategoriesWithServicesMapper;
import com.zpi.fryzland.mapper.SalonMapper;
import com.zpi.fryzland.model.*;
import com.zpi.fryzland.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/api/crud/appointment-making")
//todo: Przeniesc funkcjonalność do osobnego serwisu
public class VisitAppointmentController {
    private final SalonService salonService;
    private final AssignmentToSalonService assignmentService;
    private final EmployeeService employeeService;
    private final EmployeeQualificationService employeeQualificationService;
    private final ServiceCategoryService categoryService;
    private final ServiceService serviceService;
    private final SalonMapper salonMapper;
    private final CategoriesWithServicesMapper categoriesWithServicesMapper;

    @GetMapping("/services-and-categories/{id}")
    public ResponseEntity<CategoryWithServicesDTO> getAllCategoriesWithServicesInTheTimeSpan(@PathVariable Integer id){
        try{
            List<AssignmentToSalonModel> listOfAssignments = assignmentService.findAllAssignmentsBySalonID(id);
            System.out.println(listOfAssignments);
            List<EmployeeModel> listOfEmployees = employeeService.getAllEmployeesByAssignment(listOfAssignments);
            System.out.println(listOfEmployees);
            List<EmployeeQualificationModel> listOfQualifications = employeeQualificationService.findAllQualificationsByEmployee(listOfEmployees);
            System.out.println(listOfQualifications);
            List<ServiceCategoryModel> listOfCategories = categoryService.getAllUniqueCategoriesByEmployeeQualification(listOfQualifications);
            System.out.println(listOfCategories);
            CategoryWithServicesDTO dtoToSend = categoriesWithServicesMapper.toDTO(listOfCategories);
            return ResponseEntity.ok().body(dtoToSend);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
