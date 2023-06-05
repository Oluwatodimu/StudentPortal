package io.todimu.practice.studentportal.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentGradeRequest {

    @NonNull
    @NotEmpty
    private String matricNumber;

    @NonNull
    private Map<String, Float> courseScores;
}
