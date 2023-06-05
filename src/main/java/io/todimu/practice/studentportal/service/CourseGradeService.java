package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.model.CourseGrade;
import io.todimu.practice.studentportal.model.CourseRegistration;
import io.todimu.practice.studentportal.repository.CourseGradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseGradeService {

    private final CourseGradeRepository courseGradeRepository;

    public void createCourseGrade(CourseRegistration courseRegistration) {
        CourseGrade courseGrade = CourseGrade.builder()
                .grade((float) 0)
                .courseRegistration(courseRegistration)
                .build();
        courseGradeRepository.save(courseGrade);
    }

    public CourseGrade updateStudentGrade() {
        return null;
    }
}
