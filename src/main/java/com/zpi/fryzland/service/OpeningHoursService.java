package com.zpi.fryzland.service;

import com.zpi.fryzland.model.OpeningHoursModel;
import com.zpi.fryzland.model.SalonModel;
import com.zpi.fryzland.repository.OpeningHoursRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class OpeningHoursService {
    private final OpeningHoursRepository repository;

    public OpeningHoursModel addOpeningHours(OpeningHoursModel openingHoursModel){
        return repository.save(openingHoursModel);
    }

    public Optional<OpeningHoursModel> getOpeningHours(Integer id){
        return repository.findById(id);
    }

    public List<OpeningHoursModel> getAllOpeningHours(){
        return repository.findAll();
    }

    public void deleteOpeningHours(OpeningHoursModel openingHoursModel){
        repository.delete(openingHoursModel);
    }

    public void deleteOpeningHoursById(Integer id){
        repository.deleteById(id);
    }

    public List<OpeningHoursModel> getAllOpeningHoursBySalonModel(SalonModel salonModel){
        return repository.getAllBySalonModel(salonModel);
    }
}
