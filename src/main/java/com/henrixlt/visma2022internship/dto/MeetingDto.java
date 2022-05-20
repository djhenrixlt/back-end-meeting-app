package com.henrixlt.visma2022internship.dto;

import com.henrixlt.visma2022internship.modal.Category;
import com.henrixlt.visma2022internship.modal.Type;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class MeetingDto {

    private String name;
    private String responsiblePerson;
    private String description;
    private List<Category> category;
    private Type type;
    private LocalDateTime startDate;
    private LocalDateTime  endDate;
}
