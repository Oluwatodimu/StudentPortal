package io.todimu.practice.studentportal.web.controller;

import io.todimu.practice.studentportal.dto.TeacherUserDto;
import io.todimu.practice.studentportal.dto.request.RegisterUserRequest;
import io.todimu.practice.studentportal.service.UserService;
import io.todimu.practice.studentportal.utils.MethodAuthorityConstants;
import io.todimu.practice.studentportal.utils.ResponseConstants;
import io.todimu.practice.studentportal.web.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/teacher")
public class TeacherController {

    private final UserService userService;

    @PostMapping(value = "/register")
    @PreAuthorize(MethodAuthorityConstants.ADMIN_ROLE)
    public ResponseEntity<BaseResponse> registerTeacher(@RequestBody @Valid RegisterUserRequest registerUserRequest) {
        log.info("registering teacher with email : {}", registerUserRequest.getEmail());
        TeacherUserDto response = userService.registerTeacherUser(registerUserRequest);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.CREATED);
    }
}
