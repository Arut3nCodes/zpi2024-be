package com.zpi.fryzland.service;

import com.zpi.fryzland.model.SalonModel;
import com.zpi.fryzland.repository.SalonRepository;
import jakarta.transaction.NotSupportedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SalonService {
    private final SalonRepository salonRepository;

    public Optional<SalonModel> getSalonById(int salonID){
        return salonRepository.findById(salonID);
    }

    public List<SalonModel> getAllSalons(){
        List<SalonModel> listOfSalons = new ArrayList<>();
        for(SalonModel model : salonRepository.findAll()){
            listOfSalons.add(model);
        }
        return listOfSalons;
    }

    public SalonModel addModel(SalonModel salonModel){
        return salonRepository.save(salonModel);
    }

    public void deleteSalonById(int salonID){
        salonRepository.deleteById(salonID);
    }

    public void deleteSalon(SalonModel salonModel){
        salonRepository.delete(salonModel);
    }

    public void editSalonModel(SalonModel salonModel){
        salonRepository.save(salonModel);
    }
}
