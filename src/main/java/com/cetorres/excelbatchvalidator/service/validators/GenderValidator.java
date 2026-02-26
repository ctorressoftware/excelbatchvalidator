package com.cetorres.excelbatchvalidator.service.validators;

import com.cetorres.excelbatchvalidator.domain.ValidationResult;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component("gender")
public class GenderValidator implements Validator {
    private static final Set<String> genderValues = Set.of("M", "F");

    @Override
    public ValidationResult isValid(String value) {

        if (value == null || value.isBlank())
            return new ValidationResult(
                    "The gender must not be blank",
                    false
            );

        final String trimmedValue = value.trim();

        if (trimmedValue.length() != 1) {
            return new ValidationResult(
                    "The gender must have only one character (M or F)",
                    false
            );
        }

        if (!genderValues.contains(trimmedValue)) {
            return new ValidationResult(
                    "The gender only can be M or F",
                    false
            );
        }

        return new ValidationResult(null, true);
    }
}
