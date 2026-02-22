package com.cetorres.excelbatchvalidator.service.validators;

import org.springframework.stereotype.Component;

@Component("name")
public class NameValidator implements Validator {

    @Override
    public boolean isValid(String value) {
        return true;
    }
}
