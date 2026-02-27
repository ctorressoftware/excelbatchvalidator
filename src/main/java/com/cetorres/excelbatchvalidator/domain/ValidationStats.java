package com.cetorres.excelbatchvalidator.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

// TODO: create stats and metrics. Each worker has to write and sign this object
public class ValidationStats {
    private volatile AtomicBoolean claimed;
    private volatile AtomicLong rowsCount;
    private final List<String> signatures = Collections.synchronizedList(new ArrayList<>());

    public void setClaimed(boolean expectedValue, boolean newValue) {
        this.claimed.compareAndSet(expectedValue, newValue);
    }

    public boolean getClaimed() {
        return claimed.get();
    }

    public void setRowsCount(long expectedValue, long newValue) {
        this.rowsCount.compareAndSet(expectedValue, newValue);
    }

    public long getRowsCount() {
        return rowsCount.get();
    }

    public void addSignature(String signature) {
        this.signatures.add(signature);
    }

    public List<String> getSignatures() {
        return Collections.unmodifiableList(signatures);
    }
}
