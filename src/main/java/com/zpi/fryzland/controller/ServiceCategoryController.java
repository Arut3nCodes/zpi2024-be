package com.zpi.fryzland.controller;


import com.zpi.fryzland.dto.OpeningHoursDTO;
import com.zpi.fryzland.dto.ServiceCategoryDTO;
import com.zpi.fryzland.mapper.ServiceCategoryMapper;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.model.EmployeeQualificationModel;
import com.zpi.fryzland.model.ServiceCategoryModel;
import com.zpi.fryzland.model.ServiceModel;
import com.zpi.fryzland.service.CustomerService;
import com.zpi.fryzland.service.EmployeeQualificationService;
import com.zpi.fryzland.service.ServiceCategoryService;
import com.zpi.fryzland.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/crud/service-category")
@AllArgsConstructor
public class ServiceCategoryController {

    private final ServiceCategoryService serviceCategoryService;
    private final ServiceCategoryMapper serviceCategoryMapper;
    private final ServiceService serviceService;
    private final EmployeeQualificationService employeeQualificationService;
    private final CustomerService customerService;

    @PostMapping("")
    public ResponseEntity<ServiceCategoryDTO> addServiceCategory(@RequestBody ServiceCategoryDTO serviceCategoryDTO){
        try{
            ServiceCategoryModel serviceCategoryModel = serviceCategoryMapper.toModel(serviceCategoryDTO, false);
            serviceCategoryModel = serviceCategoryService.addCategory(serviceCategoryModel);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(serviceCategoryMapper.toDTO(serviceCategoryModel));
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ServiceCategoryDTO>> getAllServiceCategories(){
        try{
            List<ServiceCategoryDTO> listOfServiceCategories = serviceCategoryMapper.allToDTO(serviceCategoryService.getAllCategories());
            if(!listOfServiceCategories.isEmpty()){
                return ResponseEntity.ok(listOfServiceCategories);
            }
            else{
                return ResponseEntity.noContent().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<String> updateServiceCategory(@RequestBody ServiceCategoryDTO serviceCategoryDTO){
        try{
            Optional<ServiceCategoryModel> serviceCategoryModel = serviceCategoryService.getCategoryById(serviceCategoryDTO.getServiceCategoryId());
            if(serviceCategoryModel.isPresent()){
                ServiceCategoryDTO dtoToUpdate = serviceCategoryMapper.toDTO(serviceCategoryModel.get());
                dtoToUpdate.setCategoryName(serviceCategoryDTO.getCategoryName());
                dtoToUpdate.setCategoryDescription(serviceCategoryDTO.getCategoryDescription());
                serviceCategoryService.updateCategory(serviceCategoryMapper.toModel(dtoToUpdate, true));
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
    public ResponseEntity<String> deleteServiceCategory(@PathVariable int id){
        try{
            Optional<ServiceCategoryModel> serviceCategoryModel = serviceCategoryService.getCategoryById(id);
            if(serviceCategoryModel.isPresent()){
                List<ServiceModel> listOfServices = serviceService.getAllServicesByCategory(serviceCategoryModel.get());
                if(!listOfServices.isEmpty())
                    for(ServiceModel serviceModel: listOfServices)
                        serviceModel.setServiceCategoryModel(null);

                List<EmployeeQualificationModel> listOfQualifications = employeeQualificationService.getAllQualificationsByServiceCategoryID(serviceCategoryModel.get().getServiceCategoryId());
                if(!listOfQualifications.isEmpty())
                    for(EmployeeQualificationModel qualificationModel: listOfQualifications)
                        employeeQualificationService.removeEmployeeQualification(qualificationModel);

                List<CustomerModel> listOfCustomers = customerService.getAllCustomersWithSetServiceCategory(serviceCategoryModel.get());
                if(!listOfCustomers.isEmpty())
                    for(CustomerModel customerModel: listOfCustomers)
                        customerModel.setServiceCategoryModel(null);

                serviceCategoryService.deleteCategory(serviceCategoryModel.get());
                return ResponseEntity.ok().build();
            }
            else{
                return ResponseEntity.notFound().build();
            }
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
