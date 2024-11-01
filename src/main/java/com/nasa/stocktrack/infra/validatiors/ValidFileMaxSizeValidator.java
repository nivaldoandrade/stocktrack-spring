package com.nasa.stocktrack.infra.validatiors;

import com.nasa.stocktrack.infra.constraints.ValidFileMaxSizePattern;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class ValidFileMaxSizeValidator implements ConstraintValidator<ValidFileMaxSizePattern, MultipartFile> {

    private long maxSizeInBytes;

    @Override
    public void initialize(ValidFileMaxSizePattern constraintAnnotation) {
        this.maxSizeInBytes = constraintAnnotation.maxSizeInMB() * 1024 * 1024;
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if(value == null || value.isEmpty()) {
            return true;
        }

        return value.getSize() < maxSizeInBytes;
    }
}
