package io.todimu.practice.studentportal.web.controller;

import io.todimu.practice.studentportal.dto.request.AssignTeacherToCourseRequest;
import io.todimu.practice.studentportal.service.CourseTeacherService;
import io.todimu.practice.studentportal.utils.ResponseConstants;
import io.todimu.practice.studentportal.web.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/course-teacher")
public class CourseTeacherController {

    private final CourseTeacherService courseTeacherService;

    @PostMapping
    public ResponseEntity<BaseResponse> assignTeacherToCourse(@RequestBody @Validated AssignTeacherToCourseRequest request) {
        log.info("assigning teacher {} to course", request.getTeacherEmail());
        UUID response = courseTeacherService.assignTeacherToCourse(request);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.CREATED);
    }
}
