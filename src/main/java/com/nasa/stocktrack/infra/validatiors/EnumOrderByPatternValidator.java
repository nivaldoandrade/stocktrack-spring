package com.nasa.stocktrack.infra.validatiors;

import com.nasa.stocktrack.domain.enums.OrderByEnum;
import com.nasa.stocktrack.infra.constraints.EnumOrderByPattern;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumOrderByPatternValidator implements ConstraintValidator<EnumOrderByPattern, String> {
    private static List<String> acceptedValues;


    @Override
    public void initialize(EnumOrderByPattern constraintAnnotation) {
        acceptedValues = Arrays.stream(OrderByEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.isEmpty()) {
            return false;
        }

        if(!acceptedValues.contains(value.toUpperCase())) {
            String message = generateMessageOrderBy();

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
        }

        return acceptedValues.contains(value.toUpperCase());
    }

    private static String generateMessageOrderBy() {
        String values = String.join(" or ", acceptedValues);

        return "Choose orderBy: " + values;
    }
}
