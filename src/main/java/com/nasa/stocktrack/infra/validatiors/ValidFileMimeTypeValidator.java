package com.nasa.stocktrack.infra.validatiors;

import com.nasa.stocktrack.infra.constraints.ValidFileMimeTypePattern;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.tika.Tika;
import org.apache.tika.io.TikaInputStream;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public class ValidFileMimeTypeValidator implements ConstraintValidator<ValidFileMimeTypePattern, MultipartFile> {

    private final Tika tika = new Tika();

    private List<String> mineTypes;

    @Override
    public void initialize(ValidFileMimeTypePattern constraintAnnotation) {
        this.mineTypes = List.of(constraintAnnotation.mimeTypes());
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if(value == null || value.isEmpty()) {
            return true;
        }

        try {
            var detect = tika.detect(TikaInputStream.get(value.getInputStream()));
            return mineTypes.contains(detect);
        } catch (IOException e) {
            return false;
        }
    }
}
