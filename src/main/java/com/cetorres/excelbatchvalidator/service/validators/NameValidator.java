package com.cetorres.excelbatchvalidator.service.validators;

import com.cetorres.excelbatchvalidator.domain.ValidationResult;
import org.springframework.stereotype.Component;

@Component("name")
public class NameValidator implements Validator {
    private static final String NAME_REGEX = "^\\p{L}+([ '\\-]\\p{L}+)*$";
    private static final int NAME_MAX_SIZE = 50;

    @Override
    public ValidationResult isValid(String value) {

        if (value == null || value.isBlank())
            return new ValidationResult(
                    "The name must not be blank",
                    false
            );

        final String trimmedValue = value.trim();

        if (!trimmedValue.matches(NAME_REGEX)) {
            return new ValidationResult(
                    "The name may only contain letters, spaces, apostrophes, or hyphens",
                    false
            );
        }

        if (trimmedValue.length() > NAME_MAX_SIZE) {
            return new ValidationResult(
                    "The name cannot exceed 50 characters.",
                    false
            );
        }

        return new ValidationResult(null, true);
    }
}
