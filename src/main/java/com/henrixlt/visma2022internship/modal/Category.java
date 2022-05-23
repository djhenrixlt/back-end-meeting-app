package com.henrixlt.visma2022internship.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {
    CODE_MONKEY("Code Monkey"),
    HUB("Hub"),
    SHORT("Short"),
    TEAM_BUILDING("Team Building");

    private String category;
}
