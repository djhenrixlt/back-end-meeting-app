package com.henrixlt.visma2022internship.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum Category {
    CODE_MONKEY("Code Monkey"),
    HUB("Hub"),
    SHORT("Short"),
    TEAM_BUILDING("Team Building");

    private String category;

}
