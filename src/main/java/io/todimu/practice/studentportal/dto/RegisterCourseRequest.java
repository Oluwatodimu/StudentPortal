package io.todimu.practice.studentportal.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegisterCourseRequest {

    private String matricNumber;
    private List<Long> courseIdList;

}
