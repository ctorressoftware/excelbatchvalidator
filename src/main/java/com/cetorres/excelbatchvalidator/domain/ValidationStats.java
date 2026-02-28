package com.cetorres.excelbatchvalidator.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidationStats {
    private boolean claimed = false;
    private long processedRows = 0;
    private final List<String> signatures = new ArrayList<>();

    public void setClaimed(boolean claimed) {
        this.claimed = claimed;
    }

    public boolean isClaimed() {
        return claimed;
    }

    public void setProcessedRows(long processedRows) {
        this.processedRows = processedRows;
    }

    public long getProcessedRows() {
        return processedRows;
    }

    public void addSignature(String signature) {
        this.signatures.add(signature);
    }

    public List<String> getSignatures() {
        return Collections.unmodifiableList(signatures);
    }
}
