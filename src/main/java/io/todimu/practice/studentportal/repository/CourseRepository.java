package io.todimu.practice.studentportal.repository;

import io.todimu.practice.studentportal.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByName(String name);

    Optional<Course> findByCode(String code);

    Page<Course> findAllByUnits(Pageable pageable, Integer units);

}
