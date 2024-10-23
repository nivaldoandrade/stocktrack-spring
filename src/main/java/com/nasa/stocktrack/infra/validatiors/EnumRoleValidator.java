package com.nasa.stocktrack.infra.validatiors;

import com.nasa.stocktrack.domain.enums.RoleEnum;
import com.nasa.stocktrack.infra.constraints.EnumRolePattern;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class EnumRoleValidator implements ConstraintValidator<EnumRolePattern, String> {

    private static List<String> acceptedValues;

    @Override
    public void initialize(EnumRolePattern constraintAnnotation) {
        acceptedValues = Arrays.stream(RoleEnum.values())
                .map(Enum::name)
                .toList();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.isEmpty()) {
            return false;
        }

        if(!acceptedValues.contains(value.toUpperCase())) {
            String message = generateMessageRoles();
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
        }

        return acceptedValues.contains(value.toUpperCase());
    }

    private static String generateMessageRoles() {
        String values = String.join(" or ", acceptedValues);

        return "Choose roles: " + values;
    }
}
