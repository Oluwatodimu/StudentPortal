package io.todimu.practice.studentportal.web.controller;

import io.todimu.practice.studentportal.utils.MethodAuthorityConstants;
import io.todimu.practice.studentportal.web.BaseResponse.BaseResponse;
import io.todimu.practice.studentportal.dto.request.LoginRequest;
import io.todimu.practice.studentportal.security.jwt.JwtToken;
import io.todimu.practice.studentportal.service.UserService;
import io.todimu.practice.studentportal.utils.AuthoritiesConstants;
import io.todimu.practice.studentportal.utils.ResponseConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<BaseResponse> authenticateUser(@RequestBody @Validated LoginRequest loginRequest) {
        log.info("authenticating user : {}", loginRequest.getUsername());
        JwtToken jwtToken = userService.authenticateUser(loginRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.add(AuthoritiesConstants.AUTHORITIES_HEADER, "Bearer " + jwtToken.getAuthToken());
        return new ResponseEntity<>(new BaseResponse(jwtToken, ResponseConstants.SUCCESS, false), headers, HttpStatus.OK);
    }

    @GetMapping("/say-hi")
    @PreAuthorize(MethodAuthorityConstants.TEACHER_ROLE)
    public String asyHi(Authentication authentication) {
        String username = authentication.getName();
        System.out.println("nice");
        return "hi";
    }
}
