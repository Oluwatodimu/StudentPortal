package io.todimu.practice.studentportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.todimu.practice.studentportal.enumeration.StudentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@Entity
@Table(name = "student")
@EqualsAndHashCode(callSuper = true)
public class Student extends Human {

    @Enumerated(EnumType.STRING)
    @Column(name = "student_status")
    private StudentStatus studentStatus;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "student", allowSetters = true)
    private Set<CourseRegistration> courseRegistrations;
}
