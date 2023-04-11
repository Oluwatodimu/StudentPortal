package io.todimu.practice.studentportal.dto.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class CreateTeacherRequest {

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String phoneNumber;

    @NonNull
    private String email;
}
