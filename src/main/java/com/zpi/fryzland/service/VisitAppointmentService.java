package com.zpi.fryzland.service;

import com.zpi.fryzland.dto.serviceDisplay.CategoryWithServicesDTO;
import com.zpi.fryzland.mapper.CategoriesWithServicesMapper;
import com.zpi.fryzland.mapper.SalonMapper;
import com.zpi.fryzland.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VisitAppointmentService {
    private final SalonService salonService;
    private final AssignmentToSalonService assignmentService;
    private final EmployeeService employeeService;
    private final EmployeeQualificationService employeeQualificationService;
    private final ServiceCategoryService categoryService;
    private final ServiceService serviceService;
    private final SalonMapper salonMapper;
    private final CategoriesWithServicesMapper categoriesWithServicesMapper;

    public CategoryWithServicesDTO getAllCategoriesWithServicesInTheTimespan(Integer salonId){
        List<AssignmentToSalonModel> listOfAssignments = assignmentService.findAllAssignmentsBySalonID(salonId);
        List<EmployeeModel> listOfEmployees = employeeService.getAllEmployeesByAssignment(listOfAssignments);
        List<EmployeeQualificationModel> listOfQualifications = employeeQualificationService.findAllQualificationsByEmployee(listOfEmployees);
        List<ServiceCategoryModel> listOfCategories = categoryService.getAllUniqueCategoriesByEmployeeQualification(listOfQualifications);
        return categoriesWithServicesMapper.toDTO(listOfCategories);
    }

    public List<EmployeeModel> getAllEmployeesThatProvideChosenServices(Integer salonId, List<Integer> serviceIds){
        List<AssignmentToSalonModel> listOfAssignments = assignmentService.findAllAssignmentsBySalonID(salonId);
        List<EmployeeModel> listOfEmployees = employeeService.getAllEmployeesByAssignment(listOfAssignments);
        List<ServiceModel> listOfServices = serviceService.getAllServicesByIds(serviceIds);
        List<ServiceCategoryModel> listOfCategories = listOfServices.stream()
                .map(model -> model.getServiceCategoryModel())
                .distinct()
                .toList();
        return employeeQualificationService.findAllByEmployeesAndCategories(listOfEmployees, listOfCategories)
                .stream()
                .map(model -> model.getEmployeeModel())
                .distinct()
                .toList();
    }


}
