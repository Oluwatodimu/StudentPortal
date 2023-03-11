package io.todimu.practice.studentportal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "course")
public class Course extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "course_id)")
    private Long courseId;

    @ManyToOne
    private Teacher teacher;
}
