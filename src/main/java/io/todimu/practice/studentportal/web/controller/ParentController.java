package io.todimu.practice.studentportal.web.controller;

import io.todimu.practice.studentportal.dto.request.AddParentRequest;
import io.todimu.practice.studentportal.model.Parent;
import io.todimu.practice.studentportal.service.StudentService;
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

import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/parent")
public class ParentController {

    private final StudentService studentService;

    @PatchMapping(value = "/update")
    @PreAuthorize(MethodAuthorityConstants.STUDENT_AND_ADMIN_ROLES)
    public ResponseEntity<BaseResponse> addParentsData(@RequestBody @Validated AddParentRequest addParentRequest) {
        log.info("add parent info for student : {}", addParentRequest.getMatricNumber());
        Set<Parent> response = studentService.addParentData(addParentRequest);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }

    @GetMapping(value = "retrieve")
    @PreAuthorize(MethodAuthorityConstants.TEACHER_AND_ADMIN_ROLES)
    public ResponseEntity<BaseResponse> getStudentParents(@RequestParam String matricNumber) {
        log.info("getting parents for student : {}",matricNumber);
        Set<Parent> response = studentService.getStudentParents(matricNumber);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }
}
