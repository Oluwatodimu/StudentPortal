package io.todimu.practice.studentportal.service.impl;

import io.todimu.practice.studentportal.dto.TeacherDto;
import io.todimu.practice.studentportal.dto.request.CreateTeacherRequest;
import io.todimu.practice.studentportal.mapper.TeacherMapper;
import io.todimu.practice.studentportal.model.Teacher;
import io.todimu.practice.studentportal.repository.TeacherRepository;
import io.todimu.practice.studentportal.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    public TeacherDto registerTeacher(CreateTeacherRequest createTeacherRequest) {
        TeacherDto teacherDto = generateTeacherDto(createTeacherRequest);
        teacherDto = saveTeacherDto(teacherDto);
        return teacherDto;
    }

    public TeacherDto updateTeacherInfo(TeacherDto teacherDto) {
        Optional<Teacher> teacherOptional = teacherRepository.findByPhoneNumber(teacherDto.getPhoneNumber());
        Teacher teacher = getStudentIfNotEmpty(teacherOptional);
        update(teacher, teacherDto);
        teacher = teacherRepository.save(teacher);
        return toDto(teacher);
    }

    public Page<TeacherDto> getAllTeachers(Pageable pageable) {
        Page<Teacher> teacherPage = teacherRepository.findAll(pageable);
        return new PageImpl<>(teacherPage.getContent().stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toList())
        );
    }

    public TeacherDto findTeacherById(Long id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        Teacher teacher = getStudentIfNotEmpty(teacherOptional);
        return toDto(teacher);
    }

    public TeacherDto findTeacherByEmail(String email) {
        Optional<Teacher> teacherOptional = teacherRepository.findByEmail(email);
        Teacher teacher = getStudentIfNotEmpty(teacherOptional);
        return toDto(teacher);
    }

    public TeacherDto findTeacherByPhoneNumber(String phoneNumber) {
        Optional<Teacher> teacherOptional = teacherRepository.findByPhoneNumber(phoneNumber);
        Teacher teacher = getStudentIfNotEmpty(teacherOptional);
        return toDto(teacher);
    }

    public TeacherDto findTeacherByFirstAndLastName(String firstName, String lastName) {
        Optional<Teacher> teacherOptional = teacherRepository.findByFirstNameAndLastName(firstName, lastName);
        Teacher teacher = getStudentIfNotEmpty(teacherOptional);
        return toDto(teacher);
    }

    private Teacher getStudentIfNotEmpty(Optional<Teacher> teacherOptional) {
        if (teacherOptional.isEmpty()) {
            throw new RuntimeException("Student not found");
        }

        return teacherOptional.get();
    }

    private TeacherDto generateTeacherDto(CreateTeacherRequest createTeacherRequest) {
        return TeacherDto.builder()
                .firstName(createTeacherRequest.getFirstName())
                .lastName(createTeacherRequest.getLastName())
                .phoneNumber(createTeacherRequest.getPhoneNumber())
                .email(createTeacherRequest.getEmail())
                .build();
    }

    private void update(Teacher teacher, TeacherDto teacherDto) {
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setEmail(teacherDto.getEmail());
    }

    private TeacherDto saveTeacherDto(TeacherDto teacherDto) {
        Teacher teacher = teacherMapper.toEntity(teacherDto);
        teacher = teacherRepository.save(teacher);
        return toDto(teacher);
    }

    private TeacherDto toDto(Teacher teacher) {
        return teacherMapper.toDto(teacher);
    }

}
