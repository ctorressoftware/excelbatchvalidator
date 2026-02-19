package com.cetorres.excelbatchvalidator.service;

import com.cetorres.excelbatchvalidator.domain.Person;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class ExcelDataValidationService {
    private final ExcelDataExtractor excelDataExtractor;
    private final ExcelDataParser excelDataParser;
    private final DataValidatorCore dataValidatorCore;

    public ExcelDataValidationService(
            ExcelDataExtractor excelDataExtractor,
            ExcelDataParser excelDataParser) {
        this.excelDataExtractor = excelDataExtractor;
        this.excelDataParser = excelDataParser;
    }

    public List<Person> process(MultipartFile file) {
        var raw = excelDataExtractor.extract(file);
        List<Person> persons = excelDataParser.transform(raw);

        var validatorWorkers = persons.stream()
                .map(DataValidatorCore::new)
                .toList();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            List<Future<String>> futures = validatorWorkers.stream()
                    .map(executor::submit)
                    .toList();

            futures.forEach(Future::get);
        }

        return persons;
    }
}
