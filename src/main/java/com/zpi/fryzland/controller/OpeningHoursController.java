package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.EmployeeQualificationDTO;
import com.zpi.fryzland.dto.OpeningHoursDTO;
import com.zpi.fryzland.mapper.OpeningHoursMapper;
import com.zpi.fryzland.model.OpeningHoursModel;
import com.zpi.fryzland.service.OpeningHoursService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/crud/opening-hours")
@AllArgsConstructor
public class OpeningHoursController {

    private final OpeningHoursService openingHoursService;
    private final OpeningHoursMapper openinghoursMapper;

    @PostMapping("")
    public ResponseEntity<OpeningHoursDTO> addOpeningHours(@RequestBody OpeningHoursDTO openingHoursDTO){
        try{
            OpeningHoursModel openingHoursModel = openinghoursMapper.toModel(openingHoursDTO, false);
            openingHoursModel = openingHoursService.addOpeningHours(openingHoursModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(openinghoursMapper.toDTO(openingHoursModel));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<OpeningHoursDTO>> getAllOpeningHours(){
        try{
            List<OpeningHoursDTO> listOfOpeningHours = openinghoursMapper.allToDTO(openingHoursService.getAllOpeningHours());
            if(!listOfOpeningHours.isEmpty()){
                return ResponseEntity.ok(listOfOpeningHours);
            }
            else{
                return ResponseEntity.noContent().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<String> updateOpeningHours(@RequestBody OpeningHoursDTO openingHoursDTO){
        try{
            Optional<OpeningHoursModel> openingHoursModel = openingHoursService.getOpeningHours(openingHoursDTO.getOpeningHoursID());
            if(openingHoursModel.isPresent()){
                OpeningHoursDTO dtoToUpdate = openinghoursMapper.toDTO(openingHoursModel.get());
                dtoToUpdate.setWeekday(openingHoursDTO.getWeekday());
                dtoToUpdate.setOpeningHour(openingHoursDTO.getOpeningHour());
                dtoToUpdate.setClosingHour(openingHoursDTO.getClosingHour());
                dtoToUpdate.setSalonID(openingHoursDTO.getSalonID());
                openingHoursService.updateOpeningHours(openinghoursMapper.toModel(dtoToUpdate, true));
                return ResponseEntity.ok().build();
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{openingHoursID}")
    public ResponseEntity<String> deleteOpeningHours(@PathVariable int openingHoursID){
        try{
            Optional<OpeningHoursModel> openingHoursModel = openingHoursService.getOpeningHours(openingHoursID);
            if(openingHoursModel.isPresent()){
                openingHoursService.deleteOpeningHours(openingHoursModel.get());
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
