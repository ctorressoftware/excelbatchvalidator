package com.cetorres.excelbatchvalidator.domain;

public class Person {
    private final String dni;
    private final String firstName;
    private final String secondName;
    private final String firstLastname;
    private final String secondLastname;
    private final char gender;
    private final String email;
    private final String phoneNumber;

    private Person(
            String dni,
            String firstName,
            String secondName,
            String firstLastname,
            String secondLastname,
            char gender,
            String email,
            String phoneNumber) {
        this.dni = dni;
        this.firstName = firstName;
        this.secondName = secondName;
        this.firstLastname = firstLastname;
        this.secondLastname = secondLastname;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getDni() {
        return dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFirstLastname() {
        return firstLastname;
    }

    public String getSecondLastname() {
        return secondLastname;
    }

    public char getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String dni;
        private String firstName;
        private String secondName;
        private String firstLastname;
        private String secondLastname;
        private char gender;
        private String email;
        private String phoneNumber;

        public Builder dni(String dni) {
            this.dni = dni;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder secondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public Builder firstLastname(String firstLastname) {
            this.firstLastname = firstLastname;
            return this;
        }

        public Builder secondLastname(String secondLastname) {
            this.secondLastname = secondLastname;
            return this;
        }

        public Builder gender(char gender) {
            this.gender = gender;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Person build() {
            return new Person(
                    dni,
                    firstName,
                    secondName,
                    firstLastname,
                    secondLastname,
                    gender,
                    email,
                    phoneNumber
            );
        }
    }
}