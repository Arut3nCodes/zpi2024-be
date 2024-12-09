package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.ServiceCategoryDTO;
import com.zpi.fryzland.dto.ServiceDTO;
import com.zpi.fryzland.mapper.ServiceMapper;
import com.zpi.fryzland.model.ServiceModel;
import com.zpi.fryzland.service.ServiceService;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/api/crud/service")
public class ServiceController {
    private final ServiceMapper serviceMapper;
    private final ServiceService serviceService;
    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable int serviceId){
        try{
            Optional<ServiceModel> serviceModel = serviceService.getServiceById(serviceId);
            if(serviceModel.isPresent()){
                return ResponseEntity.ok(serviceMapper.toDTO(serviceModel.get()));
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/getAllById")
    public ResponseEntity<List<ServiceDTO>> getAllServicesByListOfIds(@RequestBody @NotEmpty List<Integer> listOfServiceIds){
        try{
            List<ServiceDTO> listOfServices = serviceMapper.allToDTO(serviceService.getAllServicesByIds(listOfServiceIds));
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

    @PostMapping("")
    public ResponseEntity<ServiceDTO> addService(@RequestBody ServiceDTO serviceDTO){
        try{
            ServiceModel serviceModel = serviceMapper.toModel(serviceDTO, false);
            serviceModel = serviceService.addService(serviceModel);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(serviceMapper.toDTO(serviceModel));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ServiceDTO>> getAllServices(){
        try{
            List<ServiceDTO> listOfServices = serviceMapper.allToDTO(serviceService.getAllServices());
            if(!listOfServices.isEmpty()){
                return ResponseEntity.ok(listOfServices);
            }
            else{
                return ResponseEntity.noContent().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<String> updateService(@RequestBody ServiceDTO serviceDTO){
        try{
            Optional<ServiceModel> serviceModel = serviceService.getServiceById(serviceDTO.getServiceID());
            if(serviceModel.isPresent()){
                ServiceDTO dtoToUpdate = serviceMapper.toDTO(serviceModel.get());
                dtoToUpdate.setServiceName(serviceDTO.getServiceName());
                dtoToUpdate.setServiceSpan(serviceDTO.getServiceSpan());
                dtoToUpdate.setServicePrice(serviceDTO.getServicePrice());
                dtoToUpdate.setServiceDescription(serviceDTO.getServiceDescription());
                dtoToUpdate.setServiceCategoryID(serviceDTO.getServiceCategoryID());
                serviceService.updateService(serviceMapper.toModel(dtoToUpdate, true));
                return ResponseEntity.ok().build();
            }
            else{
                return ResponseEntity.notFound().build();
            }
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{serviceID}")
    public ResponseEntity<String> deleteService(@PathVariable int serviceID){
        try{
            Optional<ServiceModel> serviceModel = serviceService.getServiceById(serviceID);
            if(serviceModel.isPresent()){
                serviceService.deleteService(serviceModel.get());
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
