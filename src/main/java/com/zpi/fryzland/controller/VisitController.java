package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.ServiceCategoryDTO;
import com.zpi.fryzland.dto.VisitWithIdsDTO;
import com.zpi.fryzland.service.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zpi.fryzland.dto.VisitDTO;
import com.zpi.fryzland.model.VisitModel;
import com.zpi.fryzland.mapper.VisitMapper;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/api/crud/visit")
public class VisitController {
    private final VisitService visitService;
    private final VisitMapper visitMapper;
    @GetMapping("/{visitID}")
    public ResponseEntity<VisitDTO> getSingleVisit(@PathVariable int visitID){
        try{
            Optional<VisitModel> visitModel = visitService.getVisitById(visitID);
            if(visitModel.isPresent()){
                return ResponseEntity.ok(visitMapper.toDTO(visitModel.get()));
            }
            else{
                return ResponseEntity.notFound().build();
            }
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/forCustomer/{customerID}")
    public ResponseEntity<List<VisitDTO>> getAllVisitsByCustomerID(@PathVariable int customerID){
        try {
            List<VisitDTO> visitList = visitService.getAllVisitsByCustomerID(customerID)
                    .stream()
                    .map(model -> visitMapper.toDTO(model))
                    .toList();
            return ResponseEntity.ok(visitList);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/forEmployee/{employeeID}")
    public ResponseEntity<List<VisitDTO>> getAllVisitsByEmployeeID(@PathVariable int employeeID){
        try{
            List<VisitDTO> visitList = visitService.getAllVisitsByEmployeeID(employeeID)
                    .stream()
                    .map(model -> visitMapper.toDTO(model))
                    .toList();
            return ResponseEntity.ok(visitList);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/withIds/forEmployee/{employeeID}")
    public ResponseEntity<List<VisitWithIdsDTO>> getAllVisitsWithIdsByEmployeeID(@PathVariable int employeeID){
        try{
            List<VisitWithIdsDTO> visitList = visitService.getAllVisitsWithIdsForEmployee(employeeID);
            return ResponseEntity.ok(visitList);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/withIds/forCustomer/{customerID}")
    public ResponseEntity<List<VisitWithIdsDTO>> getAllVisitsWithIdsByCustomerID(@PathVariable int customerID){
        try {
            List<VisitWithIdsDTO> visitList = visitService.getAllVisitsWithIdsForCustomer(customerID);
            return ResponseEntity.ok(visitList);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<VisitDTO>> getAllVisits(){
        try{
            List<VisitDTO> listOfVisits = visitMapper.allToDTO(visitService.getAllVisits());
            if(!listOfVisits.isEmpty()){
                return ResponseEntity.ok(listOfVisits);
            }
            else{
                return ResponseEntity.noContent().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/doneForCustomer/{customerID}")
    public ResponseEntity<Integer> getNumberOfFinishedVisitsByCustomerID(@PathVariable int customerID){
        try{
            int numberOfVisits = visitService.getNumberOfVisitsByCustomerID(customerID);
            return ResponseEntity.ok(numberOfVisits);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
