package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.dto.TeacherDto;
import io.todimu.practice.studentportal.dto.request.CreateTeacherRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {

    TeacherDto registerTeacher(CreateTeacherRequest createTeacherRequest);

    TeacherDto updateTeacherInfo(TeacherDto teacherDto);

    Page<TeacherDto> getAllTeachers(Pageable pageable);

    TeacherDto findTeacherById(Long id);

    TeacherDto findTeacherByEmail(String email);

    TeacherDto findTeacherByPhoneNumber(String phoneNumber);

    TeacherDto findTeacherByFirstAndLastName(String firstName, String lastName);

}
