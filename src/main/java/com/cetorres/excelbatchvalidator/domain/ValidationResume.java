package com.cetorres.excelbatchvalidator.domain;

public class ValidationResume {
    private final String error;

    public ValidationResume(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
