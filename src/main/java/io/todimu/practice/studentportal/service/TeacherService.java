package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.dto.TeacherDto;
import io.todimu.practice.studentportal.enumeration.TeacherStatus;
import io.todimu.practice.studentportal.mapper.TeacherMapper;
import io.todimu.practice.studentportal.model.Teacher;
import io.todimu.practice.studentportal.model.User;
import io.todimu.practice.studentportal.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
