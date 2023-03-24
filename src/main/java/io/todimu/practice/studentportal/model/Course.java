package io.todimu.practice.studentportal.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "course")
public class Course extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private Long code;

    @Column(name = "units")
    private Integer units;
}
