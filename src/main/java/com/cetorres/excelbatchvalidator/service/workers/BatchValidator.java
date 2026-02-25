package com.cetorres.excelbatchvalidator.service.workers;

import com.cetorres.excelbatchvalidator.domain.ValidationResume;

public interface BatchValidator {
    ValidationResume validate();
}
