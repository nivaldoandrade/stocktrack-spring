package com.nasa.stocktrack.infra.constraints;

import com.nasa.stocktrack.infra.validatiors.ValidFileMimeTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidFileMimeTypeValidator.class})
public @interface ValidFileMimeTypePattern {
    String[] mimeTypes();

    String message() default "Invalid file mime type, allowed mime type(s): {mimeTypes}";

    Class<?>[] groups() default  {};

    Class<? extends Payload>[] payload() default {};
}
