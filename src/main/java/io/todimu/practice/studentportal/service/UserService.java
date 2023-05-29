package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.dto.StudentDto;
import io.todimu.practice.studentportal.dto.StudentUserDto;
import io.todimu.practice.studentportal.dto.request.RegisterStudentRequest;
import io.todimu.practice.studentportal.enumeration.UserStatus;
import io.todimu.practice.studentportal.model.User;
import io.todimu.practice.studentportal.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final StudentService studentService;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityService authorityService;


    @Transactional
    public StudentUserDto registerStudentUser(RegisterStudentRequest registerStudentRequest) {
        //todo create a redis db to hold the activation key
        // learn to us mappers for converting to StudentDto

        User newUser = createUser(registerStudentRequest);
        userRepository.save(newUser);
        authorityService.createStudentAuthorities(newUser);
        StudentDto studentDto = studentService.registerStudent(newUser);

        return StudentUserDto.builder()
                .userId(newUser.getUserId())
                .email(newUser.getEmail())
                .matricNUmber(studentDto.getMatricNumber())
                .build();
    }

    private User createUser(RegisterStudentRequest registerStudentRequest) {
        return User.builder()
                .userId(UUID.randomUUID())
                .password(passwordEncoder.encode(registerStudentRequest.getPassword()))
                .firstName(registerStudentRequest.getFirstName())
                .lastName(registerStudentRequest.getLastName())
                .email(registerStudentRequest.getEmail())
                .phoneNumber(registerStudentRequest.getPhoneNumber())
                .userStatus(UserStatus.INACTIVE)
                .build();
    }

    public void activateStudentUser() {
        //todo create a redis db to hold the activation key

        // set activation key in redis db, using user_id as key pair
        // find by that activation key
        // once found, update user to activated and active



    }
}
