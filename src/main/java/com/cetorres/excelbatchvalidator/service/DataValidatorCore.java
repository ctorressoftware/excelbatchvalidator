package com.cetorres.excelbatchvalidator.service;

import com.cetorres.excelbatchvalidator.domain.Person;
import com.cetorres.excelbatchvalidator.domain.ValidationItem;
import com.cetorres.excelbatchvalidator.service.validators.DniValidator;
import com.cetorres.excelbatchvalidator.service.validators.Validator;

import java.util.List;
import java.util.concurrent.Callable;

public class DataValidatorCore implements Callable<String> {

    private final ValidationItem validationItem;
    private final List<Validator> validators;

    public DataValidatorCore(ValidationItem validationItem, List<Validator> validators) {
        this.validationItem = validationItem;
        this.validators = validators;
    }

    public void validate(ValidationItem validationItem) {

        /*Validator validator =

        if (!DniValidator.isValid(person.getDni())) {
            System.out.println("Error");
        }*/

    }

    @Override
    public String call() throws Exception {
        //validate();
        return "";
    }
}
