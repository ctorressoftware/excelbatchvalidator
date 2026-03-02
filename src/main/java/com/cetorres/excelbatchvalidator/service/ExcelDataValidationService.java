package com.cetorres.excelbatchvalidator.service;

import com.cetorres.excelbatchvalidator.domain.Person;
import com.cetorres.excelbatchvalidator.domain.ValidationItem;
import com.cetorres.excelbatchvalidator.domain.ValidationReport;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ExcelDataValidationService {
    private final ExcelDataExtractor excelDataExtractor;
    private final ExcelDataParser excelDataParser;
    private final DataValidatorCore dataValidatorCore;

    public ExcelDataValidationService(
            DataValidatorCore dataValidatorCore,
            ExcelDataExtractor excelDataExtractor,
            ExcelDataParser excelDataParser) {
        this.dataValidatorCore = dataValidatorCore;
        this.excelDataExtractor = excelDataExtractor;
        this.excelDataParser = excelDataParser;
    }

    public ValidationReport process(MultipartFile file) {
        var raw = excelDataExtractor.extract(file);
        List<Person> persons = excelDataParser.transform(raw);
        List<List<ValidationItem>> toValidate = excelDataParser.buildValidationItems(persons);
        return dataValidatorCore.validate(toValidate);
    }
}
