package com.nasa.stocktrack.infra.constraints;


import com.nasa.stocktrack.infra.validatiors.EnumRoleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EnumRoleValidator.class})
public @interface EnumRolePattern {
    String message() default "Role is required";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
