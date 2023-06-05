package io.todimu.practice.studentportal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "course_grade")
@EqualsAndHashCode(callSuper = true)
public class CourseGrade extends BaseEntity {

    @Column(name = "grade")
    private Float grade;

    @OneToOne
    @JoinColumn(name = "course_registration_id")
    private CourseRegistration  courseRegistration;
}
