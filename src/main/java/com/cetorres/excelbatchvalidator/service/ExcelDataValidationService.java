package com.cetorres.excelbatchvalidator.service;

import com.cetorres.excelbatchvalidator.domain.Person;
import com.cetorres.excelbatchvalidator.domain.ValidationItem;
import com.cetorres.excelbatchvalidator.domain.ValidationResume;
import com.cetorres.excelbatchvalidator.enums.DataType;
import com.cetorres.excelbatchvalidator.service.validators.BatchValidationWorker;
import com.cetorres.excelbatchvalidator.service.validators.BatchValidationWorkerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class ExcelDataValidationService {
    private final BatchValidationWorkerFactory batchValidationWorkerFactory;
    private final ExcelDataExtractor excelDataExtractor;
    private final ExcelDataParser excelDataParser;
    // private final DataValidatorCore dataValidatorCore; // TODO: put logic in this bean

    public ExcelDataValidationService(
            BatchValidationWorkerFactory batchValidationWorkerFactory,
            DataValidatorCore dataValidatorCore,
            ExcelDataExtractor excelDataExtractor,
            ExcelDataParser excelDataParser) {
        this.batchValidationWorkerFactory = batchValidationWorkerFactory;
        // this.dataValidatorCore = dataValidatorCore;
        this.excelDataExtractor = excelDataExtractor;
        this.excelDataParser = excelDataParser;
    }

    @Async
    public List<ValidationResume> process(MultipartFile file) {
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

        // dataValidatorCore.validate(validationsItems);
        List<BatchValidationWorker> workers = validationsItems.stream()
                .map(batchValidationWorkerFactory::create)
                .toList();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            List<Future<ValidationResume>> futureResume = workers.stream()
                    .map(executor::submit)
                    .toList();

            return futureResume.stream().map(future -> {
                try {
                    return future.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }).toList();
        }
    }
}
