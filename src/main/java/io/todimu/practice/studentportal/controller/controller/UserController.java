package io.todimu.practice.studentportal.controller.controller;

import io.todimu.practice.studentportal.controller.BaseResponse.BaseResponse;
import io.todimu.practice.studentportal.dto.StudentUserDto;
import io.todimu.practice.studentportal.dto.request.RegisterStudentRequest;
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
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/register/student")
    public ResponseEntity<BaseResponse> registerStudent(@RequestBody @Validated RegisterStudentRequest registerStudentRequest) {
        log.info("registering student with email : {}", registerStudentRequest.getEmail());
        StudentUserDto response = userService.registerStudentUser(registerStudentRequest);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/activate/student")
    public ResponseEntity<BaseResponse> activateStudent(@RequestParam(name = "key") String key, @RequestParam(name = "userId") UUID userId) {
        log.info("activating student account with id : {}", userId);
        StudentUserDto response = userService.activateStudentUser(key, userId);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }
}
