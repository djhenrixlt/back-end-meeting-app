package com.henrixlt.visma2022internship.entity;

import com.henrixlt.visma2022internship.modal.Category;
import com.henrixlt.visma2022internship.modal.Person;
import com.henrixlt.visma2022internship.modal.Type;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {

    private Long id;
    private String name;
    private String responsiblePerson;
    private String description;
    private Category category;
    private Type type;
    private List<Person> participant;
    private Person author;
    private LocalDate startDate;
    private LocalDate endDate;


}

