package com.henrixlt.visma2022internship.exception;

public class PersonException extends RuntimeException {

    public PersonException(String nameExist) {
        super("person with this " + nameExist + " is already exist");
    }

    public PersonException() {
        super("person do not exist");
    }
}
