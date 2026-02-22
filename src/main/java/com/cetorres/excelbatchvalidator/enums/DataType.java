package com.cetorres.excelbatchvalidator.enums;

public enum DataType {
    DNI("dni"),
    FIRST_NAME("firstName"),
    SECOND_NAME("secondName"),
    FIRST_LASTNAME("firstLastName"),
    SECOND_LASTNAME("secondLastName"),
    GENDER("gender"),
    EMAIL("email"),
    PHONE_NUMBER("phoneNumber");

    final private String name;

    DataType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
