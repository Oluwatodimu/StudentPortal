package io.todimu.practice.studentportal.repository;

import io.todimu.practice.studentportal.model.CourseGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentGradeRepository extends JpaRepository<CourseGrade, Long> {
}
