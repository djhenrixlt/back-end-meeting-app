package com.henrixlt.visma2022internship.entity;

import com.henrixlt.visma2022internship.modal.Category;
import com.henrixlt.visma2022internship.modal.Type;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


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
    private List<Category> category;
    private Type type;
    private LocalDateTime startDate;
    private LocalDateTime  endDate;
}

