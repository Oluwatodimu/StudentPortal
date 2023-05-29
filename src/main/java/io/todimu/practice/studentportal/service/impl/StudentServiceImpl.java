package io.todimu.practice.studentportal.service.impl;

import io.todimu.practice.studentportal.dto.CourseDto;
import io.todimu.practice.studentportal.dto.CourseRegistrationDto;
import io.todimu.practice.studentportal.dto.RegisterCourseRequest;
import io.todimu.practice.studentportal.dto.StudentDto;
import io.todimu.practice.studentportal.dto.request.RegisterStudentRequest;
import io.todimu.practice.studentportal.enumeration.StudentStatus;
import io.todimu.practice.studentportal.mapper.StudentMapper;
import io.todimu.practice.studentportal.model.CourseRegistration;
import io.todimu.practice.studentportal.model.Student;
import io.todimu.practice.studentportal.repository.StudentRepository;
import io.todimu.practice.studentportal.service.CourseService;
import io.todimu.practice.studentportal.service.SemesterService;
import io.todimu.practice.studentportal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final CourseService courseService;

    private final SemesterService semesterService;



    private static final int BOOKING_CODE_LENGTH = 7;

    @Override
    public StudentDto registerStudent(RegisterStudentRequest request) {
        StudentDto studentDto = generateStudentDto(request);
        studentDto = saveStudentDto(studentDto);
        return studentDto;
    }

    public Page<StudentDto> getAllStudents(Pageable pageable) {
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return new PageImpl<>(studentPage.getContent().stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList())
        );
    }

    public StudentDto getStudentByFirstAndLastName(String firstName, String lastName) {
        Optional<Student> studentOptional = studentRepository.findByFirstNameAndAndLastName(firstName, lastName);
        Student student = getStudentIfNotEmpty(studentOptional);
        return toDto(student);
    }

    public StudentDto getStudentById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        Student student = getStudentIfNotEmpty(studentOptional);
        return toDto(student);
    }

    public StudentDto getStudentByEmail(String email) {
        Optional<Student> studentOptional = studentRepository.findByEmail(email);
        Student student = getStudentIfNotEmpty(studentOptional);
        return toDto(student);
    }

    public StudentDto findByMatricNumber(String matricNumber) {
        Optional<Student> studentOptional = studentRepository.findByMatricNumber(matricNumber);
        Student student = getStudentIfNotEmpty(studentOptional);
        return toDto(student);
    }

    public StudentDto updateStudentData(StudentDto studentDto) {
        Optional<Student> studentOptional = studentRepository.findByMatricNumber(studentDto.getMatricNumber());
        Student student = getStudentIfNotEmpty(studentOptional);
        update(student, studentDto);
        student = studentRepository.save(student);
        return toDto(student);
    }

    public List<CourseRegistrationDto> registerForCourse(RegisterCourseRequest registerCourseRequest) {
        Optional<Student> studentOptional = studentRepository.findByMatricNumber(registerCourseRequest.getMatricNumber());
        Student student = getStudentIfNotEmpty(studentOptional);

        List<CourseDto> courseDtoList = registerCourseRequest.getCourseIdList().stream()
                .map(courseService::findCourseById)
                .toList();

        Set<CourseRegistration> courseRegistrationSet = student.getCourseRegistrations();
//        courseDtoList.forEach(
//                courseDto -> {}
//        );

        return null;
    }

    private String generateMatricNumber() {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random randomChar = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < BOOKING_CODE_LENGTH; i++) {
            stringBuilder.append(chars.charAt(randomChar.nextInt(chars.length())));
        }

        return stringBuilder.toString();
    }

    private StudentDto saveStudentDto(StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        student = studentRepository.save(student);
        return toDto(student);
    }

    private StudentDto generateStudentDto(RegisterStudentRequest request) {
        return StudentDto.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .studentStatus(StudentStatus.ACTIVE)
                .matricNumber(generateMatricNumber())
                .build();
    }

    private void update(Student student, StudentDto studentDto) {
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setPhoneNumber(studentDto.getPhoneNumber());

    }

    private Student getStudentIfNotEmpty(Optional<Student> studentOptional) {
        if (studentOptional.isEmpty()) {
            throw new RuntimeException("Student not found");
        }

        return studentOptional.get();
    }

    private StudentDto toDto(Student student) {
        return studentMapper.toDto(student);
    }
}
