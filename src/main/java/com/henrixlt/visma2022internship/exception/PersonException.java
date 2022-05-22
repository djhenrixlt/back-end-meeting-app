package com.henrixlt.visma2022internship.exception;

public class PersonException  extends RuntimeException{

    public PersonException(String lastName){
        super("person with this "+ lastName+ " is already exist");
    }
}
