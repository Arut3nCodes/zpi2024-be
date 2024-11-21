package com.zpi.fryzland.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FutureOrPresentDateAndTimeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FutureOrPresentDateAndTime {
    String message() default "Date time must be future!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
