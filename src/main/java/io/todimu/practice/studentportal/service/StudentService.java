package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.dto.CourseRegistrationDto;
import io.todimu.practice.studentportal.dto.RegisterCourseRequest;
import io.todimu.practice.studentportal.dto.StudentDto;
import io.todimu.practice.studentportal.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    StudentDto registerStudent(User user);

    Page<StudentDto> getAllStudents(Pageable pageable);

    StudentDto getStudentByFirstAndLastName(String firstName, String lastName);

    StudentDto getStudentById(Long id);

    StudentDto getStudentByEmail(String email);

    StudentDto findByMatricNumber(String matricNumber);

    StudentDto updateStudentData(StudentDto StudentDto);

    List<CourseRegistrationDto> registerForCourse(RegisterCourseRequest registerCourseRequest);

}
