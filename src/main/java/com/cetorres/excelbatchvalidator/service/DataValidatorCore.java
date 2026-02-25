package com.cetorres.excelbatchvalidator.service;

import com.cetorres.excelbatchvalidator.domain.ValidationItem;
import com.cetorres.excelbatchvalidator.domain.ValidationResume;
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

    public List<ValidationResume> validate(List<List<ValidationItem>> toValidate) {

        List<BatchValidationWorker> workers = toValidate.stream()
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
