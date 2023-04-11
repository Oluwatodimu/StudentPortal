package io.todimu.practice.studentportal.service.impl;

import io.todimu.practice.studentportal.dto.CourseDto;
import io.todimu.practice.studentportal.dto.request.CreateCourseRequest;
import io.todimu.practice.studentportal.mapper.CourseMapper;
import io.todimu.practice.studentportal.model.Course;
import io.todimu.practice.studentportal.repository.CourseRepository;
import io.todimu.practice.studentportal.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    public CourseDto createCourse(CreateCourseRequest createCourseRequest) {
        CourseDto courseDto = generateCourseDto(createCourseRequest);
        courseDto = saveCourseDto(courseDto);
        return courseDto;
    }

    public CourseDto findCourseById(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        Course course = getCourseIfItExists(courseOptional);
        return toDto(course);
    }


    public CourseDto findCourseByName(String name) {
        Optional<Course> courseOptional = courseRepository.findByName(name);
        Course course = getCourseIfItExists(courseOptional);
        return toDto(course);
    }

    public CourseDto findCourseByCode(Long code) {
        Optional<Course> courseOptional = courseRepository.findByCode(code);
        Course course = getCourseIfItExists(courseOptional);
        return toDto(course);
    }

    public Page<CourseDto> findAllCourses(Pageable pageable) {
        Page<Course> coursePage = courseRepository.findAll(pageable);
        return toCourseDtoPage(coursePage);
    }

    public Page<CourseDto> findAllCoursesByUnits(Pageable pageable, Integer units) {
        Page<Course> coursePage = courseRepository.findAllByUnits(pageable, units);
        return toCourseDtoPage(coursePage);
    }

    private CourseDto saveCourseDto(CourseDto courseDto) {
        Course course = courseMapper.toEntity(courseDto);
        course = courseRepository.save(course);
        return toDto(course);
    }

    private CourseDto generateCourseDto(CreateCourseRequest createCourseRequest) {
        return CourseDto.builder()
                .name(createCourseRequest.getName())
                .code(createCourseRequest.getCode())
                .units(createCourseRequest.getUnits())
                .build();
    }

    private Course getCourseIfItExists(Optional<Course> courseOptional) {
        if (courseOptional.isEmpty()) {
            throw new RuntimeException("Course not found");
        }

        return courseOptional.get();
    }

    private Page<CourseDto> toCourseDtoPage(Page<Course> coursePage) {
        return new PageImpl<>(coursePage.getContent().stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList())
        );
    }

    private CourseDto toDto(Course course) {
        return courseMapper.toDto(course);
    }

}
