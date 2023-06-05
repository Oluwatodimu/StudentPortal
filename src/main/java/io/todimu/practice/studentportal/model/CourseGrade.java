package io.todimu.practice.studentportal.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course_grade")
@EqualsAndHashCode(callSuper = true)
public class CourseGrade extends BaseEntity {

    @Column(name = "grade")
    private Float grade;

    @OneToOne
    @JoinColumn(name = "course_registration_id")
    private CourseRegistration  courseRegistration;
}
