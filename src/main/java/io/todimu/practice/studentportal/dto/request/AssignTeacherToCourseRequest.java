package io.todimu.practice.studentportal.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignTeacherToCourseRequest {

    @NonNull
    @NotEmpty
    private String courseCode;

    @NonNull
    @NotEmpty
    private String teacherEmail;
}
