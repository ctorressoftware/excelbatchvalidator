package com.cetorres.excelbatchvalidator.service;

import com.cetorres.excelbatchvalidator.domain.Person;
import com.cetorres.excelbatchvalidator.domain.ValidationItem;
import com.cetorres.excelbatchvalidator.enums.DataType;
import org.springframework.scheduling.annotation.Async;
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

    @Async
    public List<Person> process(MultipartFile file) {
        var raw = excelDataExtractor.extract(file);
        List<Person> persons = excelDataParser.transform(raw);

        var validationsItems = persons.stream()
                .map(person -> List.of(
                        new ValidationItem(DataType.DNI, person.getDni()),
                        new ValidationItem(DataType.DNI, person.getFirstName()),
                        new ValidationItem(DataType.DNI, person.getSecondName()),
                        new ValidationItem(DataType.DNI, person.getFirstLastname()),
                        new ValidationItem(DataType.DNI, person.getSecondLastname()),
                        new ValidationItem(DataType.DNI, String.valueOf(person.getGender())),
                        new ValidationItem(DataType.DNI, person.getEmail()),
                        new ValidationItem(DataType.DNI, person.getPhoneNumber()))
                )
                .toList();

        dataValidatorCore.validate(validationsItems);

        return persons;
    }
}
