package io.todimu.practice.studentportal.repository;

import io.todimu.practice.studentportal.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
}
