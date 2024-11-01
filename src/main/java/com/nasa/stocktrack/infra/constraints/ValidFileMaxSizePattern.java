package com.nasa.stocktrack.infra.constraints;

import com.nasa.stocktrack.infra.validatiors.ValidFileMaxSizeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Constraint(validatedBy = {ValidFileMaxSizeValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFileMaxSizePattern {

    String message() default "Maximum upload size exceeded. Please upload a file smaller than {maxSizeInMB}MB";

    long maxSizeInMB(); //MB

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
