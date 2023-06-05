package io.todimu.practice.studentportal.repository;

import io.todimu.practice.studentportal.model.Course;
import io.todimu.practice.studentportal.model.CourseRegistration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

    Optional<Course> findByName(String name);

    Optional<Course> findByCode(String code);

}
