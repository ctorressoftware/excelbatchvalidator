package com.cetorres.excelbatchvalidator.domain;

import com.cetorres.excelbatchvalidator.enums.DataType;

public class ValidationItem {
    private final DataType type;
    private final String value;

    public ValidationItem(DataType type, String value) {
        this.type = type;
        this.value = value;
    }

    public DataType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
