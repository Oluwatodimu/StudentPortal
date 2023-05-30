package io.todimu.practice.studentportal.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginRequestDto {

    @NotNull private String username;

    @NotNull private String password;
}
