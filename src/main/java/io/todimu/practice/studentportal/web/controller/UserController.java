package io.todimu.practice.studentportal.web.controller;

import io.todimu.practice.studentportal.annotation.RateLimited;
import io.todimu.practice.studentportal.dto.request.LoginRequest;
import io.todimu.practice.studentportal.security.jwt.JwtToken;
import io.todimu.practice.studentportal.service.UserService;
import io.todimu.practice.studentportal.utils.AuthoritiesConstants;
import io.todimu.practice.studentportal.utils.ResponseConstants;
import io.todimu.practice.studentportal.web.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;

    @RateLimited
    @PostMapping(value = "/authenticate")
    public ResponseEntity<BaseResponse> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {
        log.info("authenticating user : {}", loginRequest.getUsername());
        JwtToken jwtToken = userService.authenticateUser(loginRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.add(AuthoritiesConstants.AUTHORITIES_HEADER, "Bearer " + jwtToken.getAuthToken());
        return new ResponseEntity<>(new BaseResponse(jwtToken, ResponseConstants.SUCCESS, false), headers, HttpStatus.OK);
    }
}
