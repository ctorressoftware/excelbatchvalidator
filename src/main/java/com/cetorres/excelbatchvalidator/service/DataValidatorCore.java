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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

@Component
public class DataValidatorCore {
    private final BatchValidationWorkerFactory factory;

    public DataValidatorCore(BatchValidationWorkerFactory factory) {
        this.factory = factory;
    }

    public ValidationReport validate(List<List<ValidationItem>> toValidate) {

        var stats = new ValidationStats();
        List<BatchValidationWorker> workers = toValidate.stream()
                .map(items -> factory.create(items, stats))
                .toList();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var completionService = new ExecutorCompletionService<ValidationResume>(executor);
            for (var worker : workers) completionService.submit(worker);
            var resume = new ArrayList<ValidationResume>(workers.size());

            for (int i = 0; i < workers.size(); i++)
                resume.add(ValidationResumeHelper.get(completionService.take()));

            return ValidationReport.of(stats, resume);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for future", e);
        }
    }
}
