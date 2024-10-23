package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.TimeSlotModel;
import jdk.jfr.Registered;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends CrudRepository<TimeSlotModel, Integer> {
}
