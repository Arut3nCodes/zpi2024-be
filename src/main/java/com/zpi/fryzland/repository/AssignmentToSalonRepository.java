package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.AssignmentToSalonModel;
import com.zpi.fryzland.model.SalonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AssignmentToSalonRepository extends JpaRepository<AssignmentToSalonModel, Integer> {
    List<AssignmentToSalonModel> findAllBySalonModelAndAssignmentDate(SalonModel salonModel, Date date);
    List<AssignmentToSalonModel> findAllBySalonModel (SalonModel salonModel);
}
