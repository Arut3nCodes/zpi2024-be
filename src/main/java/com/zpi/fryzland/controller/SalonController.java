package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.SalonDTO;
import com.zpi.fryzland.mapper.SalonMapper;
import com.zpi.fryzland.model.AssignmentToSalonModel;
import com.zpi.fryzland.model.OpeningHoursModel;
import com.zpi.fryzland.model.SalonModel;
import com.zpi.fryzland.service.AssignmentToSalonService;
import com.zpi.fryzland.service.OpeningHoursService;
import com.zpi.fryzland.service.SalonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/crud/salons")
@AllArgsConstructor
public class SalonController{

    private final SalonService service;
    private final SalonMapper mapper;
    private final OpeningHoursService openingHoursService;
    private final AssignmentToSalonService assignmentToSalonService;

    @GetMapping("/{id}")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable int id){
        try{
            Optional<SalonModel> salonModel =  service.getSalonById(id);
            return salonModel.map(model -> ResponseEntity.ok(
                    mapper.toDTO(
                            model
                    )
            )).orElseGet(() -> ResponseEntity.notFound().build());
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<SalonDTO>> getAllSalons(){
        List<SalonDTO> listOfSalons = service.getAllSalons()
                .stream()
                .map(mapper::toDTO)
                .toList();
        if(listOfSalons.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listOfSalons);
    }

    @PostMapping("")
    public ResponseEntity<SalonDTO> addSalon(@RequestBody SalonDTO salonDTO){
        try{
            SalonModel salonModel = mapper.toModel(salonDTO, false);
            salonModel = service.addModel(salonModel);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(mapper.toDTO(salonModel));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<String> updateSalon(@RequestBody SalonDTO salonDTO){
        try{
            Optional<SalonModel> salonModel = service.getSalonById(salonDTO.getSalonID());
            if(salonModel.isPresent()){
                SalonDTO dtoToUpdate = mapper.toDTO(salonModel.get());
                dtoToUpdate.setSalonName(salonDTO.getSalonName());
                dtoToUpdate.setSalonDialNumber(salonDTO.getSalonDialNumber());
                dtoToUpdate.setSalonCity(salonDTO.getSalonCity());
                dtoToUpdate.setSalonStreet(salonDTO.getSalonStreet());
                dtoToUpdate.setSalonBuildingNumber(salonDTO.getSalonBuildingNumber());
                dtoToUpdate.setSalonPostalCode(salonDTO.getSalonPostalCode());
                dtoToUpdate.setLatitude(salonDTO.getLatitude());
                dtoToUpdate.setLongitude(salonDTO.getLongitude());
                service.editSalonModel(mapper.toModel(dtoToUpdate, true));
                return ResponseEntity.ok().build();
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{salonID}")
    public ResponseEntity<String> deleteSalon(@PathVariable int salonID){
        try{
            Optional<SalonModel> salonModel = service.getSalonById(salonID);
            if(salonModel.isPresent()){
                List<OpeningHoursModel> listOfOpeningHours = openingHoursService.getAllOpeningHoursBySalonModel(salonModel.get());
                if (!listOfOpeningHours.isEmpty())
                    for(OpeningHoursModel openingHoursModel : listOfOpeningHours)
                        openingHoursService.deleteOpeningHours(openingHoursModel);

                List<AssignmentToSalonModel> listOfAssignments = assignmentToSalonService.findAllAssignmentsBySalonID(salonModel.get().getSalonID());
                if(!listOfAssignments.isEmpty())
                    for(AssignmentToSalonModel assignmentToSalonModel : listOfAssignments)
                        assignmentToSalonModel.setSalonModel(null);
                service.deleteSalon(salonModel.get());
                return ResponseEntity.ok().build();
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
