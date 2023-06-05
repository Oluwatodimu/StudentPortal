package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.dto.CourseDto;
import io.todimu.practice.studentportal.dto.request.CourseToCreate;
import io.todimu.practice.studentportal.dto.request.CreateCourseRequest;
import io.todimu.practice.studentportal.mapper.CourseMapper;
import io.todimu.practice.studentportal.model.Course;
import io.todimu.practice.studentportal.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseMapper courseMapper;

    private final CourseRepository courseRepository;

    public List<CourseDto> createCourse(CreateCourseRequest createCourseRequest) {
        Set<CourseToCreate> coursesToBeRegistered = createCourseRequest.getCoursesToCreate();
        List<Course> courseList = createCourseList(coursesToBeRegistered);
        courseList = courseRepository.saveAll(courseList);
        return convertToDtoList(courseList);
    }

    private Course createCourseFromRequest(CourseToCreate courseToCreate) {
        return Course.builder()
                .name(courseToCreate.getName())
                .code(courseToCreate.getCode())
                .units(courseToCreate.getUnits())
                .build();
    }

    private List<Course> createCourseList(Set<CourseToCreate> coursesToBeRegistered) {
        return coursesToBeRegistered.stream()
                .map(this::createCourseFromRequest)
                .toList();
    }

    private List<CourseDto> convertToDtoList( List<Course> courseList) {
        return courseList.stream()
                .map(courseMapper::toDto)
                .toList();
    }


}
