package com.zpi.fryzland.validators;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = OpeningHoursValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpeningHours{
    String message() default "Closing hour must be later in the day than the opening hour";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String openingHourField();
    String closingHourField();
}
