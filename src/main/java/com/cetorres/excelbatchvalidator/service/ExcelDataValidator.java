package com.cetorres.excelbatchvalidator.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Component
public class ExcelDataValidator implements Callable<String> {

    // TODO: write logic
    public void validate() {

    }

    @Override
    public String call() throws Exception {
        validate();
        return "";
    }
}
