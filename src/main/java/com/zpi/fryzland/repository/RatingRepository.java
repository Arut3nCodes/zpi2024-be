package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.RatingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<RatingModel, Integer> {
    List<RatingModel> getAllByEmployeeModel_EmployeeID(int id);
    List<RatingModel> getAllByVisitModel_AssigmentModel_SalonModel_SalonID(int id);
}
