package com.cetorres.excelbatchvalidator.service;

import com.cetorres.excelbatchvalidator.domain.ValidationItem;
import com.cetorres.excelbatchvalidator.domain.ValidationReport;
import com.cetorres.excelbatchvalidator.domain.ValidationResume;
import com.cetorres.excelbatchvalidator.domain.ValidationStats;
import com.cetorres.excelbatchvalidator.service.workers.BatchValidationWorker;
import com.cetorres.excelbatchvalidator.service.workers.BatchValidationWorkerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class DataValidatorCore {
    private final BatchValidationWorkerFactory batchValidationWorkerFactory;

    public DataValidatorCore(BatchValidationWorkerFactory batchValidationWorkerFactory) {
        this.batchValidationWorkerFactory = batchValidationWorkerFactory;
    }

    public ValidationReport validate(List<List<ValidationItem>> toValidate) {

        var validationStats = new ValidationStats();

        List<BatchValidationWorker> workers = toValidate.stream()
                .map(items -> batchValidationWorkerFactory.create(items, validationStats))
                .toList();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            List<Future<ValidationResume>> future = workers.stream()
                    .map(executor::submit)
                    .toList();

            List<ValidationResume> resume = future.stream().map(resumeFuture -> {
                try {
                    return resumeFuture.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }).toList();

            return ValidationReport.of(validationStats, resume);
        }
    }
}
