package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.OpeningHoursModel;
import com.zpi.fryzland.model.SalonModel;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpeningHoursRepository extends JpaRepository<OpeningHoursModel, Integer> {
    List<OpeningHoursModel> getAllBySalonModel(SalonModel salonModel);
}
