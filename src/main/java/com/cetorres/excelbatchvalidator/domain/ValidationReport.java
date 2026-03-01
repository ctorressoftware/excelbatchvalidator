package com.cetorres.excelbatchvalidator.domain;

import java.util.List;

public class ValidationReport {
    private final ValidationStats stats;
    private final List<ValidationResume> resume;

    private ValidationReport(ValidationStats stats, List<ValidationResume> resume) {
        this.stats = stats;
        this.resume = resume;
    }

    public static ValidationReport of(ValidationStats stats, List<ValidationResume> resume) {
        return new ValidationReport(stats, resume);
    }

    public ValidationStats getStats() {
        return stats;
    }

    public List<ValidationResume> getResume() {
        return resume;
    }
}
