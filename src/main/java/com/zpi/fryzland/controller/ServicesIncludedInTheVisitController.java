package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.ServicesIncludedInTheVisitDTO;
import com.zpi.fryzland.mapper.ServicesIncludedInTheVisitMapper;
import com.zpi.fryzland.service.ServicesIncludedInTheVisitService;
import lombok.AllArgsConstructor;
import org.hibernate.service.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/api/crud/service-visit")
@AllArgsConstructor
public class ServicesIncludedInTheVisitController {
    private final ServicesIncludedInTheVisitService service;
    @GetMapping("/forCustomer/{id}")
    public ResponseEntity<List<ServicesIncludedInTheVisitDTO>> getAllForCustomer(@PathVariable int id){
        try{
            List<ServicesIncludedInTheVisitDTO> listOfServices = service.getAllDtoConnectionsByCustomerId(id);
            return ResponseEntity.ok(listOfServices);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/forEmployee/{id}")
    public ResponseEntity<List<ServicesIncludedInTheVisitDTO>> getAllForEmployee(@PathVariable int id){
        try{
            List<ServicesIncludedInTheVisitDTO> listOfServices = service.getAllDtoConnectionsByEmployeeId(id);
            return ResponseEntity.ok(listOfServices);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/forVisit/{id}")
    public ResponseEntity<List<ServicesIncludedInTheVisitDTO>> getAllForVisit(@PathVariable int id){
        try{
            List<ServicesIncludedInTheVisitDTO> listOfServices = service.getAllDtoConnectionsByVisitId(id);
            return ResponseEntity.ok(listOfServices);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/forSalon/{id}")
    public ResponseEntity<List<ServicesIncludedInTheVisitDTO>> getAllForSalon(@PathVariable int id){
        try{
            List<ServicesIncludedInTheVisitDTO> listOfServices = service.getAllDtoConnectionsBySalonId(id);
            return ResponseEntity.ok(listOfServices);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
