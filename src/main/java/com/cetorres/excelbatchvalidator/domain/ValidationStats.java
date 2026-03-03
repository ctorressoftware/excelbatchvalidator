package com.cetorres.excelbatchvalidator.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ValidationStats {
    private final AtomicLong processedRows = new AtomicLong(0);
    private final AtomicLong numberOfErrors = new AtomicLong(0);
    private final List<String> signatures = Collections.synchronizedList(new ArrayList<>());

    public long incrementProcessedRows() {
        return processedRows.incrementAndGet();
    }

    public long getProcessedRows() {
        return processedRows.get();
    }

    public long addNumberOfErrors(long toAdd) {
        return this.numberOfErrors.addAndGet(toAdd);
    }

    public long getNumberOfErrors() {
        return numberOfErrors.get();
    }

    public void addSignature(String signature) {
        this.signatures.add(signature);
    }

    public List<String> getSignatures() {
        synchronized (signatures) {
            return List.copyOf(signatures);
        }
    }
}
