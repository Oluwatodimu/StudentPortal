package io.todimu.practice.studentportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentUserDto {

    private UUID userId;
    private String email;
    private String matricNUmber;
}
