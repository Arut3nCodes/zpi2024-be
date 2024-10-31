package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.SalonDTO;
import com.zpi.fryzland.dto.employeeDisplay.SalonServiceIdsDTO;
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
public class VisitAppointmentController {
    private final VisitAppointmentService visitAppointmentService;

    @GetMapping("/services-and-categories/{salonId}")
    public ResponseEntity<CategoryWithServicesDTO> getAllCategoriesWithServices(@PathVariable Integer salonId){
        try{
            CategoryWithServicesDTO dtoToSend = visitAppointmentService.getAllCategoriesWithServicesInTheTimespan(salonId);
            return ResponseEntity.ok().body(dtoToSend);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeModel>> getAllEmployeesThatCanServeService(@RequestBody SalonServiceIdsDTO salonServiceIdsDTO){
        try{
            if(salonServiceIdsDTO.getSalonID() == null || salonServiceIdsDTO.getServiceIds() == null){
                throw new NullPointerException("One of the fields is null");
            }
            if(salonServiceIdsDTO.getServiceIds().isEmpty() || salonServiceIdsDTO.getServiceIds().size() > 3){
                return ResponseEntity.badRequest().build();
            }
            List<EmployeeModel> listOfEmployees = visitAppointmentService.getAllEmployeesThatProvideChosenServices(salonServiceIdsDTO.getSalonID(), salonServiceIdsDTO.getServiceIds());
            return ResponseEntity.ok(listOfEmployees);
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
