package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.dto.StudentDto;
import io.todimu.practice.studentportal.enumeration.StudentStatus;
import io.todimu.practice.studentportal.mapper.StudentMapper;
import io.todimu.practice.studentportal.model.Student;
import io.todimu.practice.studentportal.model.User;
import io.todimu.practice.studentportal.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final CourseService courseService;

    private final SemesterService semesterService;

    private static final int MATRIC_NUMBER_LENGTH = 6;

    public StudentDto registerStudent(User user) {
        Student student = createStudent(user);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    private Student createStudent(User user) {
        return Student.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .matricNumber(generateMatricNumber())
                .studentStatus(StudentStatus.ACTIVE)
                .build();
    }

    private String generateMatricNumber() {
        String chars = "0123456789";
        Random randomChar = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < MATRIC_NUMBER_LENGTH; i++) {
            stringBuilder.append(chars.charAt(randomChar.nextInt(chars.length())));
        }
        return stringBuilder.toString();
    }
}
