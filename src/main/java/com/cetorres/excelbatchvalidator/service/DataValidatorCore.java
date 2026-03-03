package com.cetorres.excelbatchvalidator.service;

import com.cetorres.excelbatchvalidator.domain.ValidationItem;
import com.cetorres.excelbatchvalidator.domain.ValidationReport;
import com.cetorres.excelbatchvalidator.domain.ValidationResume;
import com.cetorres.excelbatchvalidator.domain.ValidationStats;
import com.cetorres.excelbatchvalidator.helper.ValidationResumeHelper;
import com.cetorres.excelbatchvalidator.service.workers.BatchValidationWorker;
import com.cetorres.excelbatchvalidator.service.workers.BatchValidationWorkerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class DataValidatorCore {
    private final BatchValidationWorkerFactory factory;

    public DataValidatorCore(BatchValidationWorkerFactory factory) {
        this.factory = factory;
    }

    @Async
    public ValidationReport validate(List<List<ValidationItem>> toValidate) {

        var stats = new ValidationStats();

        List<BatchValidationWorker> workers = toValidate.stream()
                .map(items -> factory.create(items, stats))
                .toList();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            List<Future<ValidationResume>> futures = executor.invokeAll(workers);

            List<ValidationResume> resume = futures.stream()
                    .map(ValidationResumeHelper::get)
                    .toList();

            return ValidationReport.of(stats, resume);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
