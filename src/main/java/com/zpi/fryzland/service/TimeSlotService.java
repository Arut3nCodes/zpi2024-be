package com.zpi.fryzland.service;

import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.model.TimeSlotModel;
import com.zpi.fryzland.model.VisitModel;
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

    public TimeSlotModel addTimeSlot(TimeSlotModel timeSlotModel) {
        return repository.save(timeSlotModel);
    }

    public List<TimeSlotModel> addAllTimeSlots(List<TimeSlotModel> timeSlotModelList) {
        return repository.saveAll(timeSlotModelList);
    }

    public void removeTimeSlot(TimeSlotModel timeSlotModel) {
        repository.delete(timeSlotModel);
    }

    public void removeTimeSlotById(TimeSlotKey id) {
        repository.deleteById(id);
    }

    public List<TimeSlotModel> getAllTimeSlotsByEmployeeBeforeDate(EmployeeModel employeeModel, LocalDate date) {
        return repository.getAllByEmployeeModelAndTimeSlotDateBefore(employeeModel, date);
    }

    public boolean checkIfNextTimeSlotsSinceTimeExist(EmployeeModel employeeModel, LocalDate visitDate, long amountOfTimeSlots, LocalTime startTime) {
        return repository.getAllByEmployeeModelAndTimeSlotDateAndTimeSlotTimeGreaterThanEqualAndTimeSlotTimeLessThanEqual(
                employeeModel,
                visitDate,
                startTime,
                startTime.plusMinutes((amountOfTimeSlots - 1L) * 15L)
        ).isEmpty();
    }

    public boolean checkIfAnyOfTheTimeSlotsInVisitAreCollidingWithPlanned(EmployeeModel employeeModel, LocalDate visitDate, long amountOfTimeSlots, LocalTime startTime, long amountOfTimeSlotsForPlannedVisit, LocalTime startTimeOfPlannedVisit){
        LocalTime endTimeOfPlannedVisit = startTimeOfPlannedVisit.plusMinutes((amountOfTimeSlotsForPlannedVisit-1) * 15);
        List<TimeSlotModel> listOfTimeSlots = repository.getAllByEmployeeModelAndTimeSlotDateAndTimeSlotTimeGreaterThanEqualAndTimeSlotTimeLessThanEqual(employeeModel, visitDate, startTime, startTime.plusMinutes((amountOfTimeSlots-1) * 15));
        List<TimeSlotModel> afterFilter = listOfTimeSlots.stream()
                .filter(timeSlotModel -> (timeSlotModel.getTimeSlotTime().equals(startTimeOfPlannedVisit) || timeSlotModel.getTimeSlotTime().isAfter(startTimeOfPlannedVisit)) &&
                        (timeSlotModel.getTimeSlotTime().equals(endTimeOfPlannedVisit) || timeSlotModel.getTimeSlotTime().isBefore(endTimeOfPlannedVisit)))
                .toList();
        return !afterFilter.isEmpty();
    }

    public boolean checkIfNextTimeSlotsFromTimeExistExcludingCurrentVisitSlots(EmployeeModel employeeModel,  long amountOfTimeSlots, LocalDate visitDate, LocalTime startTime, LocalDate currentDate, LocalTime currentTime){
        List<TimeSlotModel> listOfTimeSlotsForRescheduled =  repository.getAllByEmployeeModelAndTimeSlotDateAndTimeSlotTimeGreaterThanEqualAndTimeSlotTimeLessThanEqual(
                employeeModel,
                visitDate,
                startTime,
                startTime.plusMinutes((amountOfTimeSlots - 1L) * 15L)
        );

        List<TimeSlotModel> listOfCurrentTimeSlots = repository.getAllByEmployeeModelAndTimeSlotDateAndTimeSlotTimeGreaterThanEqualAndTimeSlotTimeLessThanEqual(
                employeeModel,
                visitDate,
                currentTime,
                currentTime.plusMinutes((amountOfTimeSlots - 1L) * 15L)
        );

        return listOfTimeSlotsForRescheduled.stream().filter(timeSlot -> !listOfCurrentTimeSlots.contains(timeSlot)).toList().isEmpty();

    }

    public List<TimeSlotModel> createAndSaveMultipleTimeslots(EmployeeModel employeeProvidingService, LocalDate dateOfVisit, LocalTime timeStartOfVisit, long amountOfTimeSlots) {
        List<TimeSlotModel> timeSlotToSaveList = new ArrayList<>();
        for (int i = 0; i < amountOfTimeSlots; i++) {
            timeSlotToSaveList.add(new TimeSlotModel(dateOfVisit,
                    timeStartOfVisit,
                    employeeProvidingService));
            timeStartOfVisit = timeStartOfVisit.plusMinutes(15);
        }
        return repository.saveAll(timeSlotToSaveList);
    }

    public void deleteMultipleTimeslots(EmployeeModel employeeModel, LocalDate visitDate, LocalTime visitStartTime, long amountOfTimeSlots) {
        repository.deleteAllByEmployeeModelAndTimeSlotDateAndTimeSlotTimeIsGreaterThanEqualAndTimeSlotTimeIsLessThanEqual(employeeModel, visitDate, visitStartTime, visitStartTime.plusMinutes((amountOfTimeSlots-1) * 15));
    }
}