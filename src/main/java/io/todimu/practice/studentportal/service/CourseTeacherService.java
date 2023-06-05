package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.dto.request.AssignTeacherToCourseRequest;
import io.todimu.practice.studentportal.model.Course;
import io.todimu.practice.studentportal.model.CourseTeacher;
import io.todimu.practice.studentportal.model.Teacher;
import io.todimu.practice.studentportal.repository.CourseTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseTeacherService {

    private final TeacherService teacherService;

    private final CourseService courseService;

    private final CourseTeacherRepository courseTeacherRepository;

    public UUID assignTeacherToCourse(AssignTeacherToCourseRequest assignTeacherToCourseRequest) {
        Teacher teacher = teacherService.findTeacherDboByEmail(assignTeacherToCourseRequest.getTeacherEmail());
        Course course = courseService.findCourseByCourseCode(assignTeacherToCourseRequest.getCourseCode());

        CourseTeacher courseTeacher = CourseTeacher.builder()
                .course(course)
                .teacher(teacher)
                .build();
        courseTeacher = courseTeacherRepository.save(courseTeacher);
        return courseTeacher.getId();
    }
}
