package com.zpi.fryzland.service;

import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.model.TimeSlotModel;
import com.zpi.fryzland.model.compositekey.TimeSlotKey;
import com.zpi.fryzland.repository.TimeSlotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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

    public List<TimeSlotModel> getAllTimeSlotsByEmployeeBeforeDate(EmployeeModel employeeModel, LocalDate date){
        return repository.getAllByEmployeeModelAndTimeSlotDateBefore(employeeModel, date);
    }

    public boolean checkIfNextTimeSlotsSinceTimeExist(EmployeeModel employeeModel, long amountOfTimeSlots, LocalTime startTime){
        return !repository.getAllByEmployeeModelAndTimeSlotTimeGreaterThanEqualAndTimeSlotTimeLessThanEqual(
                employeeModel,
                startTime,
                startTime.minusMinutes((amountOfTimeSlots-1L) * 15L)
        ).isEmpty();
    }

    public List<TimeSlotModel> createAndSaveMultipleTimeslots(EmployeeModel employeeProvidingService, LocalDate dateOfVisit, LocalTime timeStartOfVisit, long amountOfTimeSlots){
        List<TimeSlotModel> timeSlotToSaveList = new ArrayList<>();
        for(int i = 0; i < amountOfTimeSlots-1; i++){
            timeSlotToSaveList.add(new TimeSlotModel(dateOfVisit,
                    timeStartOfVisit,
                    employeeProvidingService));
            timeStartOfVisit = timeStartOfVisit.plusMinutes(15);
        }
        return repository.saveAll(timeSlotToSaveList);
    }
}
