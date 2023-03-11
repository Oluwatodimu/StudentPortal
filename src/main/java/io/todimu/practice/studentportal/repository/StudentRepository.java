package io.todimu.practice.studentportal.repository;

import io.todimu.practice.studentportal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>  {
}
