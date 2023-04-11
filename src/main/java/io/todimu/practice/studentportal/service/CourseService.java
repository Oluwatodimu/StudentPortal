package io.todimu.practice.studentportal.service;


import io.todimu.practice.studentportal.dto.CourseDto;
import io.todimu.practice.studentportal.dto.request.CreateCourseRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    CourseDto createCourse(CreateCourseRequest createCourseRequest);

    CourseDto findCourseById(Long id);

    CourseDto findCourseByName(String name);

    CourseDto findCourseByCode(Long code);

    Page<CourseDto> findAllCourses(Pageable pageable);

    Page<CourseDto> findAllCoursesByUnits(Pageable pageable, Integer units);
}
