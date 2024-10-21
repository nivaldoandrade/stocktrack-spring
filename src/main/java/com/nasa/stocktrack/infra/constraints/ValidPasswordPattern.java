package com.nasa.stocktrack.infra.constraints;

import com.nasa.stocktrack.infra.validatiors.ValidPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidPasswordValidator.class})
public @interface ValidPasswordPattern {
    String message() default "Password must have at least {min} characters";

    int min() default 8;

    boolean required() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
