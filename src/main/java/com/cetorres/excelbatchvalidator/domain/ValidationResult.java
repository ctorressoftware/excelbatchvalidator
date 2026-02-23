package com.cetorres.excelbatchvalidator.domain;

public class ValidationResult {
    private final String message;
    private final boolean valid;

    public ValidationResult(String message, boolean valid) {
        this.message = message;
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public boolean isValid() {
        return valid;
    }
}
