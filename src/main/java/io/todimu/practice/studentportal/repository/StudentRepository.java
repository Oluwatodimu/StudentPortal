package io.todimu.practice.studentportal.repository;

import io.todimu.practice.studentportal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>  {
    
    Optional<Student> findByEmailIgnoreCase(String email);

    Optional<Student> findByMatricNumber(String matricNumber);
}
