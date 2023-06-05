package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.dto.StudentDto;
import io.todimu.practice.studentportal.dto.TeacherDto;
import io.todimu.practice.studentportal.dto.request.CourseRegistrationRequest;
import io.todimu.practice.studentportal.dto.request.StudentsRegisteredForCourseResponse;
import io.todimu.practice.studentportal.enumeration.CourseRegistrationStatus;
import io.todimu.practice.studentportal.exception.CourseAlreadyRegisteredException;
import io.todimu.practice.studentportal.model.Course;
import io.todimu.practice.studentportal.model.CourseRegistration;
import io.todimu.practice.studentportal.model.Student;
import io.todimu.practice.studentportal.repository.CourseRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseRegistrationService {

    private final StudentService studentService;

    private final TeacherService teacherService;

    private final CourseService courseService;

    private final CourseGradeService courseGradeService;

    private final CourseRegistrationRepository courseRegistrationRepository;

    public List<UUID> registerCourses(CourseRegistrationRequest registrationRequest) {
        Student student = studentService.findStudentDboByMatricNumber(registrationRequest.getMatricNumber());
        List<Course> courseList = getCoursesFromRequest(registrationRequest.getCourseCodes());
        ensureCoursesAreNotRegisteredMoreThanOnce(courseList, student);

        return courseList.stream()
                .map(course -> {
                    CourseRegistration courseRegistration = createCourseRegistrationDbo(student, course);
                    courseRegistrationRepository.save(courseRegistration);
                    courseGradeService.createCourseGrade(courseRegistration);
                    return courseRegistration.getId();
                })
                .toList();
    }

    private List<Course> getCoursesFromRequest(Set<String> courseCodes) {
        return courseCodes.stream()
                .map(courseService::findCourseByCourseCode)
                .toList();
    }

    private void ensureCoursesAreNotRegisteredMoreThanOnce(List<Course> courseList, Student student) {
        List<CourseRegistration> courseRegistrations = courseRegistrationRepository.findAllByStudent(student);
        List<Course> registeredCourses = courseService.getCoursesById(courseRegistrations);
        boolean hasIntersection = hasIntersection(courseList, registeredCourses);

        if (hasIntersection) {
            throw new CourseAlreadyRegisteredException("one or more courses already registered");
        }
    }

    private boolean hasIntersection(List<Course> unregisteredCourses, List<Course> registeredCourses) {
        boolean hasIntersection = false;
        Set<Course> registeredCourseSet = new HashSet<>(registeredCourses);

        for (Course course: unregisteredCourses) {
            if (registeredCourseSet.contains(course)) {
                hasIntersection = true;
                break;
            }
        }
        return hasIntersection;
    }

    private CourseRegistration createCourseRegistrationDbo(Student student, Course course) {
        return CourseRegistration.builder()
                .course(course)
                .student(student)
                .registrationStatus(CourseRegistrationStatus.REGISTERED)
                .build();
    }

    public StudentsRegisteredForCourseResponse findStudentsRegisteredForCourse(String courseCode) {
        Course course = courseService.findCourseByCourseCode(courseCode);
        List<CourseRegistration> courseRegistrations = courseRegistrationRepository.findAllByCourse(course);
        Set<StudentDto> registeredStudents = studentService.getStudentsFromCourseRegistration(courseRegistrations);
        Set<TeacherDto> teacherDtos = teacherService.getCourseTeachersForCourse(course);

        return StudentsRegisteredForCourseResponse.builder()
                .courseCode(course.getCode())
                .courseName(course.getName())
                .courseTeachers(teacherDtos)
                .registeredStudents(registeredStudents)
                .build();
    }
}
