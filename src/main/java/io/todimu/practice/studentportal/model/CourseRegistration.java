package io.todimu.practice.studentportal.model;

import io.todimu.practice.studentportal.enumeration.CourseRegistrationStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "course_registration")
@EqualsAndHashCode(callSuper = true)
public class CourseRegistration extends BaseEntity {

    @Column(name = "status")
    private CourseRegistrationStatus registrationStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @OneToOne(mappedBy = "course_registration")
    private CourseGrade courseGrade;
}
