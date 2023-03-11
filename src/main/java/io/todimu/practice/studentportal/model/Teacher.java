package io.todimu.practice.studentportal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "teacher")
public class Teacher extends Human {

    @Column(name = "teacher_id")
    private String teacherId;

    @OneToMany(mappedBy = "teacher")
    private Set<Course> coursesTaught;
}
