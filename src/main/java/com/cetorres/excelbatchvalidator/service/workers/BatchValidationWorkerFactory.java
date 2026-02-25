package com.cetorres.excelbatchvalidator.service.workers;

import com.cetorres.excelbatchvalidator.domain.ValidationItem;
import com.cetorres.excelbatchvalidator.service.validators.Validator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BatchValidationWorkerFactory {

    private final Map<String, Validator> validators;

    public BatchValidationWorkerFactory(Map<String, Validator> validators) {
        this.validators = validators;
    }

    public BatchValidationWorker create(List<ValidationItem> items) {
        return new BatchValidationWorker(items, validators);
    }
}
