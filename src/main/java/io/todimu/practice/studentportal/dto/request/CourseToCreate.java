package io.todimu.practice.studentportal.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CourseToCreate {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private Long code;

    @NotNull
    @NotEmpty
    private Integer units;
}
