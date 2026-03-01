package com.cetorres.excelbatchvalidator.helper;

import com.cetorres.excelbatchvalidator.domain.ValidationResume;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ValidationResumeHelper {

    public static ValidationResume get(Future<ValidationResume> future) {

        if (future == null)
            throw new IllegalArgumentException("Future is required");

        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
