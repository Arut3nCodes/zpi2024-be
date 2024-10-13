package com.zpi.fryzland.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class BirthdayDateValidator implements ConstraintValidator<BirthdayDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate birthdayDate, ConstraintValidatorContext constraintValidatorContext){
        if(birthdayDate == null) return true;
        else{
            LocalDate todayDate = LocalDate.now(
                    ZoneId.of("Europe/Warsaw")
            );

            todayDate = todayDate.minusYears(18);

            if(todayDate.isBefore(birthdayDate) || todayDate.isEqual(birthdayDate)){
                return true;
            }
            else return false;
        }
    }
}
