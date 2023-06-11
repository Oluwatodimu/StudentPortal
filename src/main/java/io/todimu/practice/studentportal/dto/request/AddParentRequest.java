package io.todimu.practice.studentportal.dto.request;

import io.todimu.practice.studentportal.model.Parent;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class AddParentRequest {

    @NotEmpty
    private String matricNumber;

    @NotNull
    private Set<Parent> parents;
}
