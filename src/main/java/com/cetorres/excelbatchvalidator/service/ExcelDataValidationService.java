package com.cetorres.excelbatchvalidator.service;

import org.springframework.stereotype.Service;

@Service
public class ExcelDataValidationOrchestrator {
    private final ExcelDataExtractor excelDataExtractor;
    private final ExcelDataParser excelDataParser;
    private final ExcelDataValidator excelDataValidator;

    public ExcelDataValidationOrchestrator(
            ExcelDataExtractor excelDataExtractor,
            ExcelDataParser excelDataParser,
            ExcelDataValidator excelDataValidator) {
        this.excelDataExtractor = excelDataExtractor;
        this.excelDataParser = excelDataParser;
        this.excelDataValidator = excelDataValidator;
    }

    // TODO: apply all the logic below, and call this in controller method.
    public void process() {
    }
}
