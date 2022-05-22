package com.henrixlt.visma2022internship.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private String firstName;
    private String lastName;
    private boolean author;
}
