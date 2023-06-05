package io.todimu.practice.studentportal.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class CourseRegistrationRequest {

    @NotNull
    @NotEmpty
    private String matricNumber;

    private String semesterName;

    @NotNull
    @NotEmpty
    private Set<String> courseCodes;
}
