package io.todimu.practice.studentportal.model;

import io.todimu.practice.studentportal.enumeration.CourseRegistrationStatus;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course_registration")
@EqualsAndHashCode(callSuper = true, exclude = "courseGrade")
public class CourseRegistration extends BaseEntity {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CourseRegistrationStatus registrationStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @OneToOne(mappedBy = "courseRegistration", cascade = CascadeType.ALL)
    private CourseGrade courseGrade;
}
