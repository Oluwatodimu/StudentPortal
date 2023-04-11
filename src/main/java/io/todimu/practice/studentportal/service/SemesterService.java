package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.dto.SemesterDto;
import io.todimu.practice.studentportal.dto.request.CreateSemesterRequest;

import java.util.List;

public interface SemesterService {

    SemesterDto createSemester(CreateSemesterRequest createSemesterRequest);

    SemesterDto findSemesterById(Long id);

    List<SemesterDto> findAllSemesters();

}
