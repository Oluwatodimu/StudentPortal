package io.todimu.practice.studentportal.dto.request;

import io.todimu.practice.studentportal.dto.CourseDto;
import lombok.Data;

import java.util.Set;

@Data
public class CreateCourseRequest {

    private Set<CourseDto> courses;
}
