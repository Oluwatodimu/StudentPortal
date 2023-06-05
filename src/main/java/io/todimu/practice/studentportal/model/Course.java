package io.todimu.practice.studentportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course")
@EqualsAndHashCode(callSuper = true)
public class Course extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private Long code;

    @Column(name = "units")
    private Integer units;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "course", allowSetters = true)
    private Set<CourseRegistration> courseRegistrations;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "course", allowSetters = true)
    private Set<CourseTeacher> courseTeachers;
}
