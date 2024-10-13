package com.zpi.fryzland.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

@Constraint(validatedBy = EmploymentDateValidator.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmploymentDate {
    String message() default "Employment date is incorrect. Employee must be over 18 years old to be employed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String employmentDateField();
    String birthdayDateField();


}
