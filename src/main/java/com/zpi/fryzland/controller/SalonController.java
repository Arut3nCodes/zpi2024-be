package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.SalonDTO;
import com.zpi.fryzland.mapper.SalonMapper;
import com.zpi.fryzland.model.SalonModel;
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
}
