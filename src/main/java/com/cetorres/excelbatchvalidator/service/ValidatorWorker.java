package com.cetorres.excelbatchvalidator.service;

import com.cetorres.excelbatchvalidator.domain.ValidationItem;
import com.cetorres.excelbatchvalidator.domain.ValidationResume;
import com.cetorres.excelbatchvalidator.service.validators.Validator;
import java.util.concurrent.Callable;

public class ValidatorWorker implements Callable<ValidationResume> {

    private final ValidationItem toValidate;
    private final Validator validator;

    public ValidatorWorker(ValidationItem toValidate, Validator validator) {
        this.toValidate = toValidate;
        this.validator = validator;
    }

    private ValidationResume validate() {

        var result = validator.isValid(toValidate.getValue());

        // TODO: maybe I gonna delete this file
        return new ValidationResume(null);
    }

    @Override
    public ValidationResume call() throws Exception {
        return validate();
    }
}
