package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.RatingAverageDTO;
import com.zpi.fryzland.dto.RatingDTO;
import com.zpi.fryzland.dto.RatingDTOWithCustomerID;
import com.zpi.fryzland.dto.ServiceCategoryDTO;
import com.zpi.fryzland.mapper.RatingMapper;
import com.zpi.fryzland.mapper.SalonMapper;
import com.zpi.fryzland.model.RatingModel;
import com.zpi.fryzland.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/api/crud/rating")
public class RatingController {
    private final RatingService service;
    private final RatingMapper mapper;
    @GetMapping("/{ratingID}")
    public ResponseEntity<RatingDTO> getRatingById(@PathVariable int ratingID){
        try{
            Optional<RatingModel> optionalRatingModel = service.getRatingById(ratingID);
            if(optionalRatingModel.isPresent()){
                return ResponseEntity.ok(mapper.toDTO(optionalRatingModel.get()));
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<RatingDTO> addRatingToDatabase(@RequestBody RatingDTO ratingDTO){
        try{
            RatingModel ratingModel = service.addRating(mapper.toModel(ratingDTO, false));
            if(ratingModel != null){
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(mapper.toDTO(ratingModel));
            }
            else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/allForSalon/{salonID}")
    public ResponseEntity<List<RatingDTO>> getAllRatingForSalon(@PathVariable int salonID){
        try{
            List<RatingDTO> ratingDTOList = service.getAllRatingsBySalonID(salonID)
                    .stream()
                    .map(model -> mapper.toDTO(model))
                    .toList();
            if(!ratingDTOList.isEmpty()){
                return ResponseEntity.ok(ratingDTOList);
            }
            else{
                return ResponseEntity.notFound().build();
            }

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/allForEmployee/{employeeID}")
    public ResponseEntity<List<RatingDTO>> getAllRatingForEmployee(@PathVariable int employeeID){
        try{
            List<RatingDTO> ratingDTOList = service.getAllRatingsByEmployeeID(employeeID)
                    .stream()
                    .map(model -> mapper.toDTO(model))
                    .toList();
            if(!ratingDTOList.isEmpty()){
                return ResponseEntity.ok(ratingDTOList);
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getAllByCustomerId/{customerID}")
    public ResponseEntity<List<RatingDTO>> getAllRatingForCustomer(@PathVariable int customerID){
        try{
            List<RatingDTO> ratingDTOList = service.getAllRatingsByCustomerId(customerID)
                    .stream()
                    .map(model -> mapper.toDTO(model))
                    .toList();
            if(!ratingDTOList.isEmpty()){
                return ResponseEntity.ok(ratingDTOList);
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/avgForSalon/{salonID}")
    public ResponseEntity<RatingAverageDTO> getAverageRatingForSalon(@PathVariable int salonID){
        try{
            float averageRating = service.calculateAverageRatingForSalonById(salonID);
            RatingAverageDTO ratingDTO = new RatingAverageDTO(averageRating);
            return ResponseEntity.ok(ratingDTO);
        }
        catch (NoSuchElementException noSuchElementException){
            noSuchElementException.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        catch(Exception e) {
              e.printStackTrace();
              return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/avgForEmployee/{employeeID}")
    public ResponseEntity<RatingAverageDTO> getAverageRatingForEmployee(@PathVariable int employeeID){
        try{
            float averageRating = service.calculateAverageRatingForEmployeeById(employeeID);
            RatingAverageDTO ratingDTO = new RatingAverageDTO(averageRating);
            return ResponseEntity.ok(ratingDTO);
        }
        catch (NoSuchElementException noSuchElementException){
            noSuchElementException.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/allForSalonWithCustomer/{salonID}")
    public ResponseEntity<List<RatingDTOWithCustomerID>> getAllRatingForSalonCustomer(@PathVariable int salonID){
        try{
            List<RatingDTOWithCustomerID> ratingDTOList = service.getAllRatingsForSalonWithCustomer(salonID);
            if(!ratingDTOList.isEmpty()){
                return ResponseEntity.ok(ratingDTOList);
            }
            else{
                return ResponseEntity.notFound().build();
            }

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("")
    public ResponseEntity<List<RatingDTO>> getAllRatings(){
        try{
            List<RatingDTO> listOfRatings = mapper.allToDTO(service.getAllRatings());
            if(!listOfRatings.isEmpty()){
                return ResponseEntity.ok(listOfRatings);
            }
            else{
                return ResponseEntity.noContent().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
