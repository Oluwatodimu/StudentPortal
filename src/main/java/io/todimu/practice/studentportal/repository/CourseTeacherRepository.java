package io.todimu.practice.studentportal.repository;

import io.todimu.practice.studentportal.model.CourseTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTeacherRepository extends JpaRepository<CourseTeacher, Long> {
}
