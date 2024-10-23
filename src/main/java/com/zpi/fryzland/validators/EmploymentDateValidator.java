package com.zpi.fryzland.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.ZoneId;
//todo: w wolnym czasie zrobic to czytelniejsze i potencjalnie napisac dokumentacje
public class EmploymentDateValidator implements ConstraintValidator<EmploymentDate, Object> {
        private String employmentDateField;
        private String birthdayDateField;

        @Override
        public void initialize (EmploymentDate constraintAnnotation){
        this.employmentDateField = constraintAnnotation.employmentDateField();
        this.birthdayDateField = constraintAnnotation.birthdayDateField();
        }
        @Override
        public boolean isValid (Object value, ConstraintValidatorContext constraintValidatorContext){
            try{
                Field employmentDateField = value.getClass().getDeclaredField(this.employmentDateField);
                Field birthdayDateField = value.getClass().getDeclaredField(this.birthdayDateField);

                employmentDateField.setAccessible(true);
                birthdayDateField.setAccessible(true);

                LocalDate employmentDate = (LocalDate) employmentDateField.get(value);
                LocalDate birthdayDate = (LocalDate) birthdayDateField.get(value);

                if(employmentDate == null || birthdayDate == null) return true;
                else {
                    return (employmentDate.minusYears(18).isAfter(LocalDate.now()) && employmentDate.isAfter(birthdayDate));
                }
            } catch (Exception e){
            e.printStackTrace();
            return false;
            }
        }
}
