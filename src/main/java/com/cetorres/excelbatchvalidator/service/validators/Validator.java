package com.cetorres.excelbatchvalidator.service.validators;

import com.cetorres.excelbatchvalidator.domain.ValidationItem;

public interface Validator {
    boolean isValid(ValidationItem validationItem);
}
