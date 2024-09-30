package com.nasa.stocktrack.infra.constraints;


import com.nasa.stocktrack.infra.validatiors.EnumOrderByPatternValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumOrderByPatternValidator.class)
public @interface EnumOrderByPattern {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
