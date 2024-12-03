package com.zpi.fryzland.service;

import com.zpi.fryzland.dto.RatingDTOWithCustomerID;
import com.zpi.fryzland.model.RatingModel;
import com.zpi.fryzland.repository.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RatingService{
    private final RatingRepository repository;

    public RatingModel addRating(RatingModel ratingModel){
        return repository.save(ratingModel);
    }

    public Optional<RatingModel> getRatingById(int id){
        return repository.findById(id);
    }

    public List<RatingModel> getAllRatingsByEmployeeID(int id){
        return repository.getAllByEmployeeModel_EmployeeID(id);
    }

    public List<RatingModel> getAllRatingsBySalonID(int id){
        return repository.getAllByVisitModel_AssigmentModel_SalonModel_SalonID(id);
    }

    public RatingModel editRating(RatingModel ratingModel){
        throw new UnsupportedOperationException();
    }

    public void removeRating(RatingModel ratingModel){
        repository.delete(ratingModel);
    }

    public void removeRatingById(int id){
        repository.deleteById(id);
    }

    public float calculateAverageRatingForEmployeeById(int id){
        return (float) getAllRatingsByEmployeeID(id)
                .stream()
                .mapToDouble(ratingModel -> ratingModel.getRatingValue())
                .average()
                .orElse(0.0);
    }

    public float calculateAverageRatingForSalonById(int id){
        return (float) getAllRatingsBySalonID(id)
                .stream()
                .mapToDouble(ratingModel -> ratingModel.getRatingValue())
                .average()
                .orElse(0.0);
    }

    public List<RatingDTOWithCustomerID> getAllRatingsForSalonWithCustomer(int salonID){
        return getAllRatingsBySalonID(salonID).stream()
                .map(model -> new RatingDTOWithCustomerID(model))
                .toList();
    }
}
