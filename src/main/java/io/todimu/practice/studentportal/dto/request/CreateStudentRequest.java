package io.todimu.practice.studentportal.dto.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class CreateStudentRequest {

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private String phoneNumber;

    @NonNull
    private String email;
}
