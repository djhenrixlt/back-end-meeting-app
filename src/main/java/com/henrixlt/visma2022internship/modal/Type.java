package com.henrixlt.visma2022internship.modal;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Type {
    LIVE("Live"),
    IN_PERSON("In person");

    private final String type;
}
