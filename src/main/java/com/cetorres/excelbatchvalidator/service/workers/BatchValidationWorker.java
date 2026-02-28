package com.cetorres.excelbatchvalidator.service.workers;

import com.cetorres.excelbatchvalidator.domain.ValidationItem;
import com.cetorres.excelbatchvalidator.domain.ValidationResume;
import com.cetorres.excelbatchvalidator.domain.ValidationStats;
import com.cetorres.excelbatchvalidator.service.validators.Validator;

import java.util.List;
import java.util.Map;

public class BatchValidationWorker extends AbstractBatchValidationWorker {

    private final ValidationStats validationStats;
    protected BatchValidationWorker(
            List<ValidationItem> itemsToValidate,
            Map<String, Validator> validators,
            ValidationStats validationStats) {
        super(itemsToValidate, validators);
        this.validationStats = validationStats;
    }

    @Override
    public ValidationResume call() {
        var processedRows = validationStats.getProcessedRows();
        validationStats.setProcessedRows(processedRows + 1);
        var result = super.validate();
        validationStats.addSignature("Thread with ID="
                + Thread.currentThread().threadId() + " did his job.");
        return result;
    }
}
