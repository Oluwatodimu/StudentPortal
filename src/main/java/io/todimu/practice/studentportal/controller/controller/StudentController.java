package io.todimu.practice.studentportal.controller.controller;

import io.todimu.practice.studentportal.dto.StudentDto;
import io.todimu.practice.studentportal.dto.request.RegisterStudentRequest;
import io.todimu.practice.studentportal.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    private static final Integer PAGE_NUMBER = 0;

    private static final Integer PAGE_SIZE = 10;


//    @PostMapping(value = "/register")
//    public ResponseEntity<StudentDto> register(@RequestBody RegisterStudentRequest request) {
//        // todo run controller advice for handling errors
//        log.info("Creating new student");
//        StudentDto studentDto = studentService.registerStudent(request);
//        return new ResponseEntity<>(studentDto, HttpStatus.CREATED);
//    }

    @GetMapping(value = "")
    public ResponseEntity<Page<StudentDto>> getAllStudents(@RequestParam(required = false) Integer pageNumber,
                                                           @RequestParam(required = false) Integer pageSize) {

        log.info("getting all students");
        Pageable pagingAndSortedByCreatedDateDesc = createSortedPageableObject(pageNumber, pageSize);
        Page<StudentDto> studentDtoPage = studentService.getAllStudents(pagingAndSortedByCreatedDateDesc);
        return new ResponseEntity<>(studentDtoPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{firstName}/{lastName}")
    public ResponseEntity<StudentDto> getStudentByFirstAndLastName(@PathVariable String firstName,
                                                                         @PathVariable String lastName) {

        log.info(String.format("get student with name %s %s", firstName, lastName));
        StudentDto studentDto = studentService.getStudentByFirstAndLastName(firstName, lastName);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @GetMapping(value = "/id")
    public ResponseEntity<StudentDto> getStudentById(@RequestParam Long id) {
        log.info(String.format("get student with id %s", id));
        StudentDto studentDto = studentService.getStudentById(id);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @GetMapping(value = "/email")
    public ResponseEntity<StudentDto> getStudentByEmail(@RequestParam String email) {
        log.info(String.format("get student with email %s", email));
        StudentDto studentDto = studentService.getStudentByEmail(email);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @GetMapping(value = "/matric-number")
    public ResponseEntity<StudentDto> getStudentByMatricNumber(@RequestParam String matricNumber) {
        log.info(String.format("get student with matric number %s", matricNumber));
        StudentDto studentDto = studentService.findByMatricNumber(matricNumber);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<StudentDto> updateStudentData(@RequestBody StudentDto studentDto) {
        log.info("updating student with matric number : {}", studentDto.getMatricNumber());
        StudentDto updatedStudentDto = studentService.updateStudentData(studentDto);
        return new ResponseEntity<>(updatedStudentDto, HttpStatus.CREATED);
    }

    private Pageable createSortedPageableObject(Integer pageNumber, Integer pageSize) {
        Pageable pagingAndSortedByCreatedDateDesc;

        if (pageNumber == null || pageSize == null) {
            pagingAndSortedByCreatedDateDesc = PageRequest.of(PAGE_NUMBER, PAGE_SIZE, Sort.by("createdDate").descending());
        } else {
            pagingAndSortedByCreatedDateDesc = PageRequest.of(pageNumber, pageSize, Sort.by("createdDate").descending());
        }

        return  pagingAndSortedByCreatedDateDesc;
    }

}
