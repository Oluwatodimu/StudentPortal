package io.todimu.practice.studentportal.web.controller;

import io.todimu.practice.studentportal.web.BaseResponse.BaseResponse;
import io.todimu.practice.studentportal.dto.StudentUserDto;
import io.todimu.practice.studentportal.dto.request.RegisterUserRequest;
import io.todimu.practice.studentportal.service.UserService;
import io.todimu.practice.studentportal.utils.ResponseConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/student")
public class StudentController {

    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<BaseResponse> registerStudent(@RequestBody @Validated RegisterUserRequest registerUserRequest) {
        log.info("registering student with email : {}", registerUserRequest.getEmail());
        StudentUserDto response = userService.registerStudentUser(registerUserRequest);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/activate")
    public ResponseEntity<BaseResponse> activateStudent(@RequestParam(name = "key") String key, @RequestParam(name = "userId") UUID userId) {
        log.info("activating student account with id : {}", userId);
        StudentUserDto response = userService.activateStudentUser(key, userId);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }
}
