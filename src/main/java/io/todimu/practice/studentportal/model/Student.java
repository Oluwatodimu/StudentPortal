package io.todimu.practice.studentportal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "student")
public class Student extends Human {

    @Column(name = "student_id")
    private String studentId;

    @OneToMany(mappedBy = "student")
    private Set<Course> registeredCourses;
}
