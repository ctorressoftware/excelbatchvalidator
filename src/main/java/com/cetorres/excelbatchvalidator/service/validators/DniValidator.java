package com.cetorres.excelbatchvalidator.service.validators;

public class DniValidator implements Validator {

    public static boolean isValid(String dni) {
        if (dni == null) return false;

        dni = dni.replaceAll("[^0-9]", "");

        if (!dni.matches("\\d{11}")) {
            return false;
        }

        int sum = 0;

        for (int i = 0; i < 10; i++) {
            int digit = Character.getNumericValue(dni.charAt(i));
            int multiplier = (i % 2 == 0) ? 1 : 2;

            int result = digit * multiplier;

            if (result > 9) {
                result -= 9;
            }

            sum += result;
        }

        int calculatedCheckDigit = (10 - (sum % 10)) % 10;
        int realCheckDigit = Character.getNumericValue(dni.charAt(10));

        return calculatedCheckDigit == realCheckDigit;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
