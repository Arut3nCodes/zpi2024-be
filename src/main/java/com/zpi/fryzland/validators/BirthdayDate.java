package com.zpi.fryzland.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * This annotation is used to check if employee birthday date is set up correctly
 * (Employee must be over 18 years old)
 */
@Constraint(validatedBy = BirthdayDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BirthdayDate {
    String message() default "Birthday date is incorrect. Employee must be over 18 years old";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
