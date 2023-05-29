package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.dto.*;
import io.todimu.practice.studentportal.dto.request.RegisterUserRequest;
import io.todimu.practice.studentportal.enumeration.UserStatus;
import io.todimu.practice.studentportal.model.User;
import io.todimu.practice.studentportal.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final StudentService studentService;

    private final TeacherService teacherService;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityService authorityService;

    private final ActivationKeyService activationKeyService;

    @Transactional
    public StudentUserDto registerStudentUser(RegisterUserRequest registerUserRequest) {
        User newUser = createUser(registerUserRequest, UserStatus.INACTIVE);
        userRepository.save(newUser);
        authorityService.createStudentAuthorities(newUser);
        StudentDto studentDto = studentService.registerStudent(newUser);
        ActivationKey activationKey = activationKeyService.createActivationKey(newUser.getUserId().toString());

        return StudentUserDto.builder()
                .userId(newUser.getUserId())
                .email(newUser.getEmail())
                .matricNUmber(studentDto.getMatricNumber())
                .activationKey(activationKey.getActivationKey())
                .build();
    }

    private User createUser(RegisterUserRequest registerUserRequest, UserStatus userStatus) {
        return User.builder()
                .userId(UUID.randomUUID())
                .password(passwordEncoder.encode(registerUserRequest.getPassword()))
                .firstName(registerUserRequest.getFirstName())
                .lastName(registerUserRequest.getLastName())
                .email(registerUserRequest.getEmail())
                .phoneNumber(registerUserRequest.getPhoneNumber())
                .userStatus(userStatus)
                .build();
    }

    public StudentUserDto activateStudentUser(String key, UUID userId) {
        ActivationKey activationKey = activationKeyService.findByActivationKey(key);

        if (activationKey == null) {
            throw new BadCredentialsException("bad credentials sent");
        }

        Optional<User> savedUserOptional = userRepository.findByUserId(userId);

        if (savedUserOptional.isEmpty()) {
            throw new RuntimeException("user not found");
        }

        User savedUser = savedUserOptional.get();
        savedUser.setActivated(true);
        savedUser.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(savedUser);

        return StudentUserDto.builder()
                .email(savedUser.getEmail())
                .userId(savedUser.getUserId())
                .build();
    }

    public TeacherUserDto registerTeacherUser(RegisterUserRequest registerUserRequest) {
        User newTeacherUser = createUser(registerUserRequest, UserStatus.ACTIVE);
        newTeacherUser.setActivated(true);
        userRepository.save(newTeacherUser);
        authorityService.createTeacherAuthorities(newTeacherUser);
        TeacherDto teacherDto = teacherService.registerTeacher(newTeacherUser);

        return TeacherUserDto.builder()
                .userId(newTeacherUser.getUserId())
                .email(teacherDto.getEmail())
                .build();
    }
}
