package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.model.TimeSlotModel;
import com.zpi.fryzland.model.compositekey.TimeSlotKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlotModel, TimeSlotKey> {
    List<TimeSlotModel> getAllByEmployeeModelAndTimeSlotDateBefore(EmployeeModel employeeModel, Date beforeDate);
}
