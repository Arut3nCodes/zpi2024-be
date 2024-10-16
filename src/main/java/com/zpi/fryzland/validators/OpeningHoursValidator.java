package com.zpi.fryzland.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class OpeningHoursValidator implements ConstraintValidator<OpeningHours, Object> {
    private String openingHourField;
    private String closingHourField;

    @Override
    public void initialize (OpeningHours constraintAnnotation){
        this.openingHourField = constraintAnnotation.openingHourField();
        this.closingHourField = constraintAnnotation.closingHourField();
    }
    @Override
    public boolean isValid (Object value, ConstraintValidatorContext constraintValidatorContext){
        try{
            Field openingHourField = value.getClass().getDeclaredField(this.openingHourField);
            Field closingHourField = value.getClass().getDeclaredField(this.closingHourField);

            openingHourField.setAccessible(true);
            closingHourField.setAccessible(true);

            LocalTime openingHour = (LocalTime) openingHourField.get(value);
            LocalTime closingHour = (LocalTime) closingHourField.get(value);

            if(openingHour == null || closingHour == null) return true;
            else {
                return openingHour.isBefore(closingHour);
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
