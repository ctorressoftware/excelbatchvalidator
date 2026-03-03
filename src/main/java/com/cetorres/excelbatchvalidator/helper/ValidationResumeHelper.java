package com.cetorres.excelbatchvalidator.helper;

import com.cetorres.excelbatchvalidator.domain.ValidationResume;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ValidationResumeHelper {

    public static ValidationResume get(Future<ValidationResume> future) {
        if (future == null) throw new IllegalArgumentException("Future is required");

        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for future", e);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException re) throw re;
            if (cause instanceof Error err) throw err;
            throw new RuntimeException("Task failed", cause);
        }
    }
}
