package com.cetorres.excelbatchvalidator.service.validators;

import com.cetorres.excelbatchvalidator.domain.ValidationResult;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component("phone_number")
public class PhoneNumberValidator implements Validator {

    private static final Pattern PHONE_PATTERN = Pattern.compile("^(809|829|849)\\d{7}$");

    @Override
    public ValidationResult isValid(String value) {

        if (value == null || value.isBlank()) {
            return new ValidationResult(
                    "The phone number must not be blank",
                    false
            );
        }

        String trimmedValue = value.trim();

        if (!PHONE_PATTERN.matcher(trimmedValue).matches()) {
            return new ValidationResult(
                    "The phone number must be a valid Dominican phone number (10 digits, starting with 809, 829, or 849)",
                    false
            );
        }

        return new ValidationResult(null, true);
    }
}