package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.AssignmentToSalonModel;
import com.zpi.fryzland.model.SalonModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface AssignmentToSalonRepository extends CrudRepository<AssignmentToSalonModel, Integer>{
    public List<AssignmentToSalonModel> findAllBySalonModelAndAssignmentDate(SalonModel salonModel, Date date);
    public List<AssignmentToSalonModel> findAllBySalonModel (SalonModel salonModel);
}
