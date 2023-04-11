package io.todimu.practice.studentportal.dto.request;

import lombok.Data;

@Data
public class CreateCourseRequest {
    private String name;
    private Long code;
    private Integer units;
}
