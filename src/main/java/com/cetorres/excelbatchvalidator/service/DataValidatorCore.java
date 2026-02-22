package com.cetorres.excelbatchvalidator.service;

import com.cetorres.excelbatchvalidator.domain.ValidationItem;
import com.cetorres.excelbatchvalidator.domain.ValidationResume;
import com.cetorres.excelbatchvalidator.service.validators.Validator;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class DataValidatorCore {
    private final Map<String, Validator> validators;

    public DataValidatorCore(Map<String, Validator> validators) {
        this.validators = validators;
    }

    public void validate(List<List<ValidationItem>> batchToValidate) {

        var workers = batchToValidate.stream()
                .flatMap(Collection::stream)
                .map(rowToValidate -> {

                    final String type = rowToValidate.getType().getName().toLowerCase();

                    if (!validators.containsKey(type))
                        throw new RuntimeException("Cannot find a valid validator for specific type: " + type);

                    var validator = validators.get(type);

                    return new ValidatorWorker(rowToValidate, validator);
                })
                .toList();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            List<Future<ValidationResume>> futureResume = workers.stream()
                    .map(executor::submit)
                    .toList();

            var resume = futureResume.stream().map(future -> {
                try {
                    return future.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }).toList();

            for (int i = 0; i < resume.size(); i++) {
                System.out.println((i+1) + " " + resume.get(i).getError());
            }
        }
    }
}
