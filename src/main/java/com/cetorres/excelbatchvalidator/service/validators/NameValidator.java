package com.cetorres.excelbatchvalidator.service.validators;

import com.cetorres.excelbatchvalidator.domain.ValidationResult;
import org.springframework.stereotype.Component;

@Component("name")
public class NameValidator implements Validator {

    @Override
    public ValidationResult isValid(String value) {
        return new ValidationResult("Good result", true);
    }
}
