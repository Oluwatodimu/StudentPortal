package io.todimu.practice.studentportal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "course_grade")
@EqualsAndHashCode(callSuper = true)
public class CourseGrade extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_registration_id", unique = true)
    private CourseGrade courseGrade;
}
