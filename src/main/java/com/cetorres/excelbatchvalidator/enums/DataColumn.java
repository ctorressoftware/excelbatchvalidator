package com.cetorres.excelbatchvalidator.enums;

public enum DataColumn {
    DNI(1),
    FIRST_NAME(2),
    SECOND_NAME(3),
    FIRST_LASTNAME(4),
    SECOND_LASTNAME(5),
    GENDER(6),
    EMAIL(7),
    PHONE_NUMBER(8);

    private final int index;

    DataColumn(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
