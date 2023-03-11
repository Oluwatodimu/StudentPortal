package io.todimu.practice.studentportal.repository;

import io.todimu.practice.studentportal.model.StudentGrades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentGradesRepository extends JpaRepository<StudentGrades, Long> {
}
