package io.todimu.practice.studentportal.web.controller;

import io.todimu.practice.studentportal.dto.request.CourseRegistrationRequest;
import io.todimu.practice.studentportal.dto.request.StudentsRegisteredForCourseResponse;
import io.todimu.practice.studentportal.service.CourseRegistrationService;
import io.todimu.practice.studentportal.utils.MethodAuthorityConstants;
import io.todimu.practice.studentportal.utils.ResponseConstants;
import io.todimu.practice.studentportal.web.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/course-registration")
public class CourseRegistrationController {

    private final CourseRegistrationService courseRegistrationService;

    @PostMapping
    @PreAuthorize(MethodAuthorityConstants.STUDENT_AND_ADMIN_ROLES)
    public ResponseEntity<BaseResponse> registerCourses(@RequestBody @Validated CourseRegistrationRequest registrationRequest) {
        log.info("registering courses for student: {}",registrationRequest.getMatricNumber());
        List<UUID> uuidList = courseRegistrationService.registerCourses(registrationRequest);
        return new ResponseEntity<>(new BaseResponse(uuidList, ResponseConstants.SUCCESS, false), HttpStatus.CREATED);
    }

    @GetMapping("/retrieve")
    @PreAuthorize(MethodAuthorityConstants.TEACHER_AND_ADMIN_ROLES)
    public ResponseEntity<BaseResponse> getRegisteredStudentsForCourse(@RequestParam String courseCode) {
        log.info("getting registered students for course: {}", courseCode);
        StudentsRegisteredForCourseResponse response = courseRegistrationService.findStudentsRegisteredForCourse(courseCode);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }
}
