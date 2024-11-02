package com.zpi.fryzland.service;

import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.model.TimeSlotModel;
import com.zpi.fryzland.model.compositekey.TimeSlotKey;
import com.zpi.fryzland.repository.TimeSlotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TimeSlotService {
    private final TimeSlotRepository repository;

    public TimeSlotModel addTimeSlot(TimeSlotModel timeSlotModel){
        return repository.save(timeSlotModel);
    }

    public List<TimeSlotModel> addAllTimeSlots(List<TimeSlotModel> timeSlotModelList){
        return repository.saveAll(timeSlotModelList);
    }

    public void removeTimeSlot(TimeSlotModel timeSlotModel){
        repository.delete(timeSlotModel);
    }

    public void removeTimeSlotById(TimeSlotKey id){
        repository.deleteById(id);
    }

    public List<TimeSlotModel> getAllTimeSlotsByEmployeeBeforeDate(EmployeeModel employeeModel, Date date){
        return repository.getAllByEmployeeModelAndTimeSlotDateBefore(employeeModel, date);
    }
}
