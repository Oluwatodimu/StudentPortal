package io.todimu.practice.studentportal.web.controller;

import io.todimu.practice.studentportal.annotation.RateLimited;
import io.todimu.practice.studentportal.dto.request.UpdateStudentGradeRequest;
import io.todimu.practice.studentportal.dto.response.GPAResponse;
import io.todimu.practice.studentportal.dto.response.GetStudentGradesResponse;
import io.todimu.practice.studentportal.service.CourseGradeService;
import io.todimu.practice.studentportal.service.StudentService;
import io.todimu.practice.studentportal.utils.GPACalculator;
import io.todimu.practice.studentportal.utils.MethodAuthorityConstants;
import io.todimu.practice.studentportal.utils.ResponseConstants;
import io.todimu.practice.studentportal.web.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/grade")
public class CourseGradeController {

    private final StudentService studentService;

    private final GPACalculator gpaCalculator;

    private final CourseGradeService courseGradeService;

    @RateLimited
    @GetMapping(value = "/retrieve")
    @PreAuthorize(MethodAuthorityConstants.TEACHER_AND_ADMIN_ROLES)
    public ResponseEntity<BaseResponse> getStudentGrades(@RequestParam(value = "matricNumber") String matricNumber) {
        log.info("getting grades for student : {}", matricNumber);
        GetStudentGradesResponse response = studentService.getStudentGrades(matricNumber);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }

    @RateLimited
    @PatchMapping("/update")
    @PreAuthorize(MethodAuthorityConstants.TEACHER_AND_ADMIN_ROLES)
    public ResponseEntity<BaseResponse> updateStudentGrades(@RequestBody @Valid UpdateStudentGradeRequest request) {
        log.info("updating grades for student : {}", request.getMatricNumber());
        courseGradeService.updateStudentGrade(request);
        return new ResponseEntity<>(new BaseResponse(null, ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }

    @RateLimited
    @GetMapping(value = "/gpa")
    @PreAuthorize(MethodAuthorityConstants.TEACHER_AND_ADMIN_ROLES)
    public ResponseEntity<BaseResponse> getStudentGPA(@RequestParam(value = "matricNumber") String matricNumber) {
        log.info("calculating  gpa for student : {}", matricNumber);
        GPAResponse response = gpaCalculator.calculateStudentGpa(matricNumber);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }
}
