package io.todimu.practice.studentportal.repository;

import io.todimu.practice.studentportal.model.Course;
import io.todimu.practice.studentportal.model.CourseRegistration;
import io.todimu.practice.studentportal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, UUID> {

    List<CourseRegistration> findAllByStudent(Student student);

    List<CourseRegistration> findAllByCourse(Course course);
}
