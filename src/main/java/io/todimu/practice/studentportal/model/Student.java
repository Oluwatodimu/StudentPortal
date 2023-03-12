package io.todimu.practice.studentportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "student")
public class Student extends Human {

    @Column(name = "student_id")
    private String studentId;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "student", allowSetters = true)
    private Set<Course> registeredCourses = new HashSet<>();
}
