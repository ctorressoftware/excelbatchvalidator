package com.cetorres.excelbatchvalidator.service.validators;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dni")
public class DniValidator implements Validator {

    @Override
    public boolean isValid(String value) {
        if (value == null || value.isBlank()) return false;

        String cleanValue = value.replaceAll("[^0-9]", "");

        if (!cleanValue.matches("\\d{11}")) return false;

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

        return calculatedCheckDigit == realCheckDigit;
    }
}
