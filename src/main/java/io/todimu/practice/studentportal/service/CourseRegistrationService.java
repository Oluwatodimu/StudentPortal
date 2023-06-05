package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.dto.request.CourseRegistrationRequest;
import io.todimu.practice.studentportal.enumeration.CourseRegistrationStatus;
import io.todimu.practice.studentportal.model.Course;
import io.todimu.practice.studentportal.model.CourseRegistration;
import io.todimu.practice.studentportal.model.Student;
import io.todimu.practice.studentportal.repository.CourseRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseRegistrationService {

    private final StudentService studentService;

    private final CourseService courseService;

    private final CourseGradeService courseGradeService;

    private final CourseRegistrationRepository courseRegistrationRepository;

    public List<UUID> registerCourses(CourseRegistrationRequest registrationRequest) {
        Student student = studentService.findStudentDboByMatricNumber(registrationRequest.getMatricNumber());
        List<Course> courseList = getCoursesFromRequest(registrationRequest.getCourseCodes());
        List<UUID> courseRegistrationIds = courseList.stream()
                .map(course -> {
                    CourseRegistration courseRegistration = createCourseRegistrationDbo(student, course);
                    courseRegistrationRepository.save(courseRegistration);
                    courseGradeService.createCourseGrade(courseRegistration);
                    return courseRegistration.getId();
                })
                .toList();

        return courseRegistrationIds;
    }

    private List<Course> getCoursesFromRequest(Set<String> courseCodes) {
        return courseCodes.stream()
                .map(courseService::findCourseByCourseCode)
                .toList();
    }

    public CourseRegistration createCourseRegistrationDbo(Student student, Course course) {
        return CourseRegistration.builder()
                .course(course)
                .student(student)
                .registrationStatus(CourseRegistrationStatus.REGISTERED)
                .build();
    }
}
