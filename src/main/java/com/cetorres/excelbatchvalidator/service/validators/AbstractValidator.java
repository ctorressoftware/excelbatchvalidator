package com.cetorres.excelbatchvalidator.service.validators;

import com.cetorres.excelbatchvalidator.domain.ValidationItem;
import com.cetorres.excelbatchvalidator.enums.DataType;

import java.util.List;

public abstract class AbstractValidator implements Validator {
    private ValidationItem validationItem;
    private List<Validator> validators;

    private Validator setValidationStrategyComponent() {

        switch (validationItem.getType()) {
            case DataType.DNI -> validators.getFirst();
            case DataType.FIRST_NAME -> validators.getFirst();
            case DataType.SECOND_NAME -> validators.getFirst();
            case DataType.FIRST_LASTNAME -> validators.getFirst();
            case DataType.SECOND_LASTNAME -> validators.getFirst();
            case DataType.GENDER -> validators.getFirst();
            case DataType.EMAIL -> validators.getFirst();
            case DataType.PHONE_NUMBER -> validators.getFirst();
        }
        return null;

        // if (validationItem.getType().equals(DataType.FIRST_NAME) {}
    }

}
