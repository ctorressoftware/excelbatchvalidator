package com.cetorres.excelbatchvalidator.service;

import org.springframework.stereotype.Service;

@Service
public class ExcelDataOrchestrator {
    private final ExcelDataExtractor excelDataExtractor;
    private final ExcelDataParser excelDataParser;
    private final ExcelDataValidator excelDataValidator;

    public ExcelDataOrchestrator(
            ExcelDataExtractor excelDataExtractor,
            ExcelDataParser excelDataParser,
            ExcelDataValidator excelDataValidator) {
        this.excelDataExtractor = excelDataExtractor;
        this.excelDataParser = excelDataParser;
        this.excelDataValidator = excelDataValidator;
    }

    // TODO: apply all the logic below, and call this in controller method.
    public void parseDataAndValidate() {
    }
}
