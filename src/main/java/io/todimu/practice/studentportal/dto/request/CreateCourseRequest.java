package io.todimu.practice.studentportal.dto.request;

import lombok.Data;

import java.util.Set;

@Data
public class CreateCourseRequest {

    private Set<CourseToCreate> coursesToCreate;
}
