package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.dto.CourseDto;
import io.todimu.practice.studentportal.dto.request.CreateCourseRequest;
import io.todimu.practice.studentportal.exception.CourseNotFoundException;
import io.todimu.practice.studentportal.mapper.CourseMapper;
import io.todimu.practice.studentportal.model.Course;
import io.todimu.practice.studentportal.model.CourseRegistration;
import io.todimu.practice.studentportal.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseMapper courseMapper;

    private final CourseRepository courseRepository;

    public List<CourseDto> createCourse(CreateCourseRequest createCourseRequest) {
        Set<CourseDto> coursesToBeRegistered = createCourseRequest.getCourses();
        List<Course> courseList = createCourseList(coursesToBeRegistered);
        courseList = courseRepository.saveAll(courseList);
        return convertToDtoList(courseList);
    }

    private Course createCourseFromRequest(CourseDto courseDto) {
        return Course.builder()
                .name(courseDto.getName())
                .code(courseDto.getCode())
                .units(courseDto.getUnits())
                .build();
    }

    private List<Course> createCourseList(Set<CourseDto> coursesToBeRegistered) {
        return coursesToBeRegistered.stream()
                .map(this::createCourseFromRequest)
                .toList();
    }

    private List<CourseDto> convertToDtoList( List<Course> courseList) {
        return courseList.stream()
                .map(courseMapper::toDto)
                .toList();
    }

    public Course findCourseByCourseCode(String code) {
        return courseRepository.findByCode(code)
                .orElseThrow(() -> new CourseNotFoundException("course not found"));
    }

    public List<Course> getCoursesById(List<CourseRegistration> courseRegistrations) {
        return courseRegistrations.stream()
                .map(courseRegistration -> findCourseById(courseRegistration.getCourse().getId()))
                .collect(Collectors.toList());
    }

    public Course findCourseById(UUID id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("course not found"));
    }

    public Page<CourseDto> findAllCourses(Pageable pageable) {
        Page<Course> courses = courseRepository.findAll(pageable);
        return new PageImpl<>(courses.getContent()
                .stream()
                .map(courseMapper::toDto)
                .toList()
        );
    }
}
