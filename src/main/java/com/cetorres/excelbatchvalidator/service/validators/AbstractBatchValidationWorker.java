package com.cetorres.excelbatchvalidator.service.validators;

import com.cetorres.excelbatchvalidator.domain.ValidationItem;
import com.cetorres.excelbatchvalidator.domain.ValidationResult;
import com.cetorres.excelbatchvalidator.domain.ValidationResume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;

public abstract class AbstractBatchValidationWorker implements BatchValidator, Callable<ValidationResume> {
    private final List<ValidationItem> itemsToValidate;
    private final Map<String, Validator> validators;

    protected AbstractBatchValidationWorker(
            List<ValidationItem> itemsToValidate,
            Map<String, Validator> validators) {
        this.itemsToValidate = itemsToValidate;
        this.validators = validators;
    }

    private Validator resolveValidator(ValidationItem validationItem) {

        final String type = validationItem.getType().getName();

        return Optional.ofNullable(validators.get(type))
                .orElseThrow(() -> new IllegalStateException(
                        "No validator found for type: " + type
                ));
    }

    @Override
    public ValidationResume validate() {

        final var errors = new ArrayList<String>();

        for (ValidationItem itemToValidate : itemsToValidate) {
            Validator validator = resolveValidator(itemToValidate);
            ValidationResult result = validator.isValid(itemToValidate.getValue());
            if (!result.isValid()) {
                errors.add(result.getMessage());
            }
        }

        return new ValidationResume(errors);
    }
}
