package com.cetorres.excelbatchvalidator.service.workers;

import com.cetorres.excelbatchvalidator.domain.ValidationItem;
import com.cetorres.excelbatchvalidator.domain.ValidationResume;
import com.cetorres.excelbatchvalidator.service.validators.Validator;

import java.util.List;
import java.util.Map;

public class BatchValidationWorker extends AbstractBatchValidationWorker {

    protected BatchValidationWorker(
            List<ValidationItem> itemsToValidate,
            Map<String, Validator> validators) {
        super(itemsToValidate, validators);
    }

    @Override
    public ValidationResume call() throws Exception {
        return super.validate();
    }
}
