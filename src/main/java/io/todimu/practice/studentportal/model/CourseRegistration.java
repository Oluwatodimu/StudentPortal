package io.todimu.practice.studentportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "matric_number")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "semester_id")
    private Semester semester;

//    @JsonIgnoreProperties(value = "course_registration", allowSetters = true)
//    @OneToOne(mappedBy = "course_registration", cascade = CascadeType.ALL)
//    private CourseGrade courseGrade;
}
