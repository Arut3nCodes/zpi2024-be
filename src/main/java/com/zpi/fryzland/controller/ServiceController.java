package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.ServiceDTO;
import com.zpi.fryzland.mapper.ServiceMapper;
import com.zpi.fryzland.model.ServiceModel;
import com.zpi.fryzland.service.ServiceService;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/api/crud/service")
public class ServiceController {
    ServiceService service;
    ServiceMapper mapper;
    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable int serviceId){
        try{
            Optional<ServiceModel> serviceModel = service.getServiceById(serviceId);
            if(serviceModel.isPresent()){
                return ResponseEntity.ok(mapper.toDTO(serviceModel.get()));
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/getAllById")
    public ResponseEntity<List<ServiceDTO>> getAllServicesByListOfIds(@RequestBody @NotEmpty List<Integer> listOfServiceIds){
        try{
            List<ServiceDTO> listOfServices = mapper.allToDTO(service.getAllServicesByIds(listOfServiceIds));
            if(!listOfServices.isEmpty()){
                return ResponseEntity.ok(listOfServices);
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
