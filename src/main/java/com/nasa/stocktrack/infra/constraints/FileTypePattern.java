package com.nasa.stocktrack.infra.constraints;

import com.nasa.stocktrack.infra.validatiors.FileTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileTypeValidator.class})
public @interface FileTypePattern {
    String message() default "File type is not accepted.";

    String[] allowedExtensions();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
