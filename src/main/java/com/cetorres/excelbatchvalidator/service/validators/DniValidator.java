package com.cetorres.excelbatchvalidator.service.validators;

import com.cetorres.excelbatchvalidator.domain.ValidationResult;
import org.springframework.stereotype.Component;

@Component("dni")
public class DniValidator implements Validator {

    @Override
    public ValidationResult isValid(String value) {
        if (value == null || value.isBlank())
            return new ValidationResult("DNI with no characters", false);

        String cleanValue = value.replaceAll("[^0-9]", "");

        if (!cleanValue.matches("\\d{11}"))
            return new ValidationResult("Incorrect length for DNI: " + value, false);

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int digit = Character.getNumericValue(cleanValue.charAt(i));
            int multiplier = (i % 2 == 0) ? 1 : 2;
            int result = digit * multiplier;

            if (result > 9) {
                result -= 9;
            }

            sum += result;
        }

        int calculatedCheckDigit = (10 - (sum % 10)) % 10;
        int realCheckDigit = Character.getNumericValue(cleanValue.charAt(10));

        if (calculatedCheckDigit == realCheckDigit) {
            return new ValidationResult("DNI " + value + ": OK", true);
        } else {
            return new ValidationResult("Invalid DNI: " + value, false);
        }
    }
}
