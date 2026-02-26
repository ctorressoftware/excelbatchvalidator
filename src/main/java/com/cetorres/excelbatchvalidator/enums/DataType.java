package com.cetorres.excelbatchvalidator.enums;

public enum DataType {
    DNI("dni"),
    NAME("name"),
    LASTNAME("lastname"),
    GENDER("gender"),
    EMAIL("email"),
    PHONE_NUMBER("phone_number");

    final private String name;

    DataType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
