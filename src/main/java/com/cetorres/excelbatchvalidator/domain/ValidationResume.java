package com.cetorres.excelbatchvalidator.domain;

import java.util.List;

public class ValidationResume {
    private final List<String> errors;

    public ValidationResume(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
