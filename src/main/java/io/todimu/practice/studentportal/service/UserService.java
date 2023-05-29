package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.dto.ActivationKey;
import io.todimu.practice.studentportal.dto.StudentDto;
import io.todimu.practice.studentportal.dto.StudentUserDto;
import io.todimu.practice.studentportal.dto.request.RegisterStudentRequest;
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

    private final PasswordEncoder passwordEncoder;

    private final AuthorityService authorityService;

    private final ActivationKeyService activationKeyService;

    @Transactional
    public StudentUserDto registerStudentUser(RegisterStudentRequest registerStudentRequest) {
        User newUser = createUser(registerStudentRequest);
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
}
