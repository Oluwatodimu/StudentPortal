package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.dto.TeacherDto;
import io.todimu.practice.studentportal.enumeration.TeacherStatus;
import io.todimu.practice.studentportal.exception.UserNotFoundException;
import io.todimu.practice.studentportal.mapper.TeacherMapper;
import io.todimu.practice.studentportal.model.*;
import io.todimu.practice.studentportal.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    public TeacherDto registerTeacher(User user) {
        Teacher teacher = createTeacher(user);
        teacher = teacherRepository.save(teacher);
        return teacherMapper.toDto(teacher);
    }

    private Teacher createTeacher(User user) {
        return Teacher.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .teacherStatus(TeacherStatus.ACTIVE)
                .build();
    }

    public Teacher findTeacherDboByEmail(String email) {
        return teacherRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    public Set<TeacherDto> getCourseTeachersForCourse(Course course) {
        Set<CourseTeacher> courseTeachers = course.getCourseTeachers();
        return courseTeachers.stream()
                .map(CourseTeacher::getTeacher)
                .map(teacherMapper::toDto)
                .collect(Collectors.toSet());
    }
}
