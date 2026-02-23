package com.cetorres.excelbatchvalidator.service.validators;

import com.cetorres.excelbatchvalidator.domain.ValidationResult;

public interface Validator {
    ValidationResult isValid(String value);
}
