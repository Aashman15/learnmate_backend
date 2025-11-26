package com.aashman.learnmate.validation.annotations;

import com.aashman.learnmate.validation.validators.UniqueFieldValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueFieldValidator.class)
@Documented
public @interface UniqueField {

    String message() default "Duplicate values found for field";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /** The name of the field to check uniqueness for */
    String fieldName();
}
