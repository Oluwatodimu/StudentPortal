package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.dto.request.UpdateStudentGradeRequest;
import io.todimu.practice.studentportal.model.Course;
import io.todimu.practice.studentportal.model.CourseGrade;
import io.todimu.practice.studentportal.model.CourseRegistration;
import io.todimu.practice.studentportal.model.Student;
import io.todimu.practice.studentportal.repository.CourseGradeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseGradeService {

    private final StudentService studentService;

    private final CourseRegistrationService courseRegistrationService;

    private final CourseService courseService;

    private final CourseGradeRepository courseGradeRepository;

    @PostConstruct
    public void init() {
        courseRegistrationService.setCourseGradeService(this);
    }

    public void createCourseGrade(CourseRegistration courseRegistration) {
        CourseGrade courseGrade = CourseGrade.builder()
                .grade((float) 0)
                .courseRegistration(courseRegistration)
                .build();
        courseGradeRepository.save(courseGrade);
    }

    @Transactional
    public void updateStudentGrade(UpdateStudentGradeRequest updateStudentGradeRequest) {
        Student student = studentService.findStudentDboByMatricNumber(updateStudentGradeRequest.getMatricNumber());
        updateStudentGradeRequest.getCourseScores().forEach(
                ((courseCode, courseGrade) -> {
                    Course existingCourse = courseService.findCourseByCourseCode(courseCode);
                    CourseRegistration courseRegistration = courseRegistrationService.findByCourseAndStudent(existingCourse, student);
                    CourseGrade grade = courseRegistration.getCourseGrade();
                    grade.setGrade(courseGrade);
                    courseGradeRepository.save(grade);
                })
        );
    }
}
