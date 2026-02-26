package com.cetorres.excelbatchvalidator.service.validators;

import com.cetorres.excelbatchvalidator.domain.ValidationResult;
import org.springframework.stereotype.Component;

@Component("lastname")
public class LastnameValidator implements Validator {
    private static final String LASTNAME_REGEX = "^\\p{L}+([ '\\-]\\p{L}+)*$";
    private static final int LASTNAME_MAX_SIZE = 50;

    @Override
    public ValidationResult isValid(String value) {

        if (value == null || value.isBlank())
            return new ValidationResult(
                    "The lastname must not be blank",
                    false
            );

        final String trimmedValue = value.trim();

        if (!trimmedValue.matches(LASTNAME_REGEX)) {
            return new ValidationResult(
                    "The lastname may only contain letters, spaces, apostrophes, or hyphens",
                    false
            );
        }

        if (trimmedValue.length() > LASTNAME_MAX_SIZE) {
            return new ValidationResult(
                    "The lastname cannot exceed 50 characters.",
                    false
            );
        }

        return new ValidationResult(null, true);
    }
}
