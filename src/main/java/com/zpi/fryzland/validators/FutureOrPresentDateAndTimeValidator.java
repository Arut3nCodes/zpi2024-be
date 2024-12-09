package com.zpi.fryzland.validators;

import com.zpi.fryzland.model.TimeSlotModel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;

public class FutureOrPresentDateAndTimeValidator implements ConstraintValidator<FutureOrPresentDateAndTime, TimeSlotModel> {

    @Override
    public boolean isValid(TimeSlotModel timeSlotModel, ConstraintValidatorContext constraintValidatorContext){
        try{

            LocalDate date = timeSlotModel.getTimeSlotDate();
            LocalTime time = timeSlotModel.getTimeSlotTime();

            if(date == null || time == null){
                return false;
            }
            else{
                return (date.isAfter(LocalDate.now()) || (date.isEqual(LocalDate.now())
                        && time.isAfter(LocalTime.now())));
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
