package io.todimu.practice.studentportal.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class CreateSemesterRequest {
    private Date startDate;
    private Date endDate;
}
