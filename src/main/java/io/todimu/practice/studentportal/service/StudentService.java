package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.dto.StudentDto;
import io.todimu.practice.studentportal.dto.request.UpdateStudentRequest;
import io.todimu.practice.studentportal.enumeration.StudentStatus;
import io.todimu.practice.studentportal.exception.UserNotFoundException;
import io.todimu.practice.studentportal.mapper.StudentMapper;
import io.todimu.practice.studentportal.model.Student;
import io.todimu.practice.studentportal.model.User;
import io.todimu.practice.studentportal.repository.StudentRepository;
import io.todimu.practice.studentportal.utils.ValueGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private UserService userService;

    private final CourseService courseService;

    private final SemesterService semesterService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


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
                .matricNumber(ValueGenerator.generateMatricNumber())
                .studentStatus(StudentStatus.ACTIVE)
                .build();
    }

    public StudentDto getStudentDetails(String emailOrMatricNumber) { // emailOrMatricNumber can be email or string
        StudentDto studentDto = null;

        if (emailOrMatricNumber.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")) {
            studentDto = findByEmail(emailOrMatricNumber);
        } else if (emailOrMatricNumber.matches("\\d{6}")) {
            studentDto = findByMatricNumber(emailOrMatricNumber);
        }

        return studentDto;
    }

    private StudentDto findByMatricNumber(String matricNumber) {
        return studentRepository.findByMatricNumber(matricNumber)
                .map(studentMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("student not found"));
    }

    private StudentDto findByEmail(String email) {
        return studentRepository.findByEmailIgnoreCase(email)
                .map(studentMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("student not found"));
    }

    public Page<StudentDto> findAllStudents(Pageable pageable) {
        Page<Student> students = studentRepository.findAll(pageable);
        return new PageImpl<>(students.getContent()
                        .stream()
                        .map(studentMapper::toDto)
                        .collect(Collectors.toList()));
    }

    public StudentDto updateStudentDetails(UpdateStudentRequest updateRequest) {
        Student studentToUpdate = studentRepository.findByEmailIgnoreCase(updateRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("user not found"));

        studentToUpdate.setFirstName(updateRequest.getFirstName());
        studentToUpdate.setLastName(updateRequest.getLastName());
        userService.updateStudentUserDetails(updateRequest);
        studentRepository.save(studentToUpdate);
        return studentMapper.toDto(studentToUpdate);
    }

    // todo create custom annotations
}
