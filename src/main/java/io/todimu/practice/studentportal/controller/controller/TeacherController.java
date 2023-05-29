package io.todimu.practice.studentportal.controller.controller;

import io.todimu.practice.studentportal.controller.BaseResponse.BaseResponse;
import io.todimu.practice.studentportal.dto.TeacherUserDto;
import io.todimu.practice.studentportal.dto.request.RegisterUserRequest;
import io.todimu.practice.studentportal.service.UserService;
import io.todimu.practice.studentportal.utils.ResponseConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/teacher")
public class TeacherController {

    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<BaseResponse> registerTeacher(@RequestBody @Validated RegisterUserRequest registerUserRequest) {
        log.info("registering teacher with email : {}", registerUserRequest.getEmail());
        TeacherUserDto response = userService.registerTeacherUser(registerUserRequest);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.CREATED);
    }

}
