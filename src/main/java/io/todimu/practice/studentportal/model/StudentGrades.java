package io.todimu.practice.studentportal.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "student_grades")
public class StudentGrades extends BaseEntity {

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    @Column(name = "score")
    private Float score;
}
