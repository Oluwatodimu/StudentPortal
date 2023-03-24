package io.todimu.practice.studentportal.repository;

import io.todimu.practice.studentportal.model.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
}
