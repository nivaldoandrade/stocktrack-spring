package com.nasa.stocktrack.infra.validatiors;

import com.nasa.stocktrack.infra.constraints.FileTypePattern;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileTypeValidator implements ConstraintValidator<FileTypePattern, MultipartFile> {

    private static List<String> allowsExtensions;
    @Override
    public void initialize(FileTypePattern constraintAnnotation) {
        allowsExtensions = List.of(constraintAnnotation.allowedExtensions());
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if(value == null || value.isEmpty()) {
            return true;
        }

        String originalFilename = value.getOriginalFilename();

        String extension = originalFilename != null
                ? originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase()
                : null;

        return allowsExtensions.contains(extension);
    }
}
