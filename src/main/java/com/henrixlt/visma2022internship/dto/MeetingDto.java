package com.henrixlt.visma2022internship.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class MeetingDto {

    private Long id;
    private String name;
    private String responsiblePerson;
    private String description;
    private String category;
    private String type;
    private List<PersonDto> participant;
    private PersonDto author;
    private LocalDate startDate;
    private LocalDate endDate;
}
