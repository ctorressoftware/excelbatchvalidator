package com.cetorres.excelbatchvalidator.service.validators;

import com.cetorres.excelbatchvalidator.domain.ValidationResult;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component("email")
public class EmailValidator implements Validator {

    private static final int MAX_LENGTH = 254;
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    @Override
    public ValidationResult isValid(String value) {

        if (value == null || value.isBlank())
            return new ValidationResult(
                    "The email must not be blank",
                    false
            );

        String trimmedValue = value.trim();

        if (trimmedValue.length() > MAX_LENGTH) {
            return new ValidationResult(
                    "The email cannot exceed 254 characters",
                    false
            );
        }

        if (!EMAIL_PATTERN.matcher(trimmedValue).matches()) {
            return new ValidationResult(
                    "The email format is invalid",
                    false
            );
        }

        return new ValidationResult(null, true);
    }
}