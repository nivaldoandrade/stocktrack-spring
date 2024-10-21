package com.nasa.stocktrack.infra.validatiors;

import com.nasa.stocktrack.infra.constraints.ValidPasswordPattern;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPasswordValidator implements ConstraintValidator<ValidPasswordPattern, String> {
    private int min;
    private boolean required;

    @Override
    public void initialize(ValidPasswordPattern constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(!required && value == null) {
            return true;
        }

        if(value == null) {
            return false;
        }

        return value.length() >= min;
    }
}
