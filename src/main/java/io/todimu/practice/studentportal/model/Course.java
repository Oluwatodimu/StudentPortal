package io.todimu.practice.studentportal.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "course")
public class Course extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "course_id)")
    private Long courseId;

    @Column(name = "units")
    private Integer units;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    // todo might need to remove the student thing here
}
