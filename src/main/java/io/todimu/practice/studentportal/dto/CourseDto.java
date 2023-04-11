package io.todimu.practice.studentportal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseDto {
    private String name;
    private Long code;
    private Integer units;
}
