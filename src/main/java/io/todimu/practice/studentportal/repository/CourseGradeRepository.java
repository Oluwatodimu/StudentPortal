package io.todimu.practice.studentportal.repository;

import io.todimu.practice.studentportal.model.CourseGrade;
import io.todimu.practice.studentportal.model.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseGradeRepository extends JpaRepository<CourseGrade, UUID> {

    Optional<CourseGrade> findByCourseRegistration(CourseRegistration courseRegistration);
}
