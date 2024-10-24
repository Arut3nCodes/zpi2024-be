package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.SalonDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("api/crud/salon")
@AllArgsConstructor
public class SalonController{
//    @GetMapping("/{id}")
//    public ResponseEntity<SalonDTO> getItemById(@RequestParam int id){
//        try{
//            SalonDTO salonDTO =
//        }catch(Exception e){
//            return ResponseEntity.badRequest().build();
//        }
//    }
}
