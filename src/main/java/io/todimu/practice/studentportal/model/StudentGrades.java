package io.todimu.practice.studentportal.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "student_grades")
public class StudentGrades extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "score")
    private Float score;
}
