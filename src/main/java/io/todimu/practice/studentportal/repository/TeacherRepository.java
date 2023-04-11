package io.todimu.practice.studentportal.repository;

import io.todimu.practice.studentportal.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findById(Long id);

    Optional<Teacher> findByEmail(String id);

    Optional<Teacher> findByPhoneNumber(String phoneNumber);

    Optional<Teacher> findByFirstNameAndLastName(String firstName, String lastName);
}
