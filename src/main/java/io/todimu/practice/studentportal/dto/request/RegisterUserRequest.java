package io.todimu.practice.studentportal.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequest {

    @NonNull private String firstName;

    @NonNull private String lastName;

    @Pattern(regexp = "^\\d{13}$")
    @NonNull private String phoneNumber;

    @Email
    @NonNull private String email;

    @Pattern(regexp = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$")
    @NonNull private String password;
}
