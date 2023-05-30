package io.todimu.practice.studentportal.controller.controller;

import io.todimu.practice.studentportal.controller.BaseResponse.BaseResponse;
import io.todimu.practice.studentportal.dto.request.LoginRequestDto;
import io.todimu.practice.studentportal.security.jwt.JwtToken;
import io.todimu.practice.studentportal.service.UserService;
import io.todimu.practice.studentportal.utils.AuthoritiesConstants;
import io.todimu.practice.studentportal.utils.ResponseConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<BaseResponse> authenticateUser(@RequestBody LoginRequestDto loginRequestDto) {
        log.info("authenticating user : {}", loginRequestDto.getUsername());
        JwtToken jwtToken = userService.authenticateUser(loginRequestDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add(AuthoritiesConstants.AUTHORITIES_HEADER, "Bearer " + jwtToken.getAuthToken());
        return new ResponseEntity<>(new BaseResponse(jwtToken, ResponseConstants.SUCCESS, false), headers, HttpStatus.OK);
    }
}
