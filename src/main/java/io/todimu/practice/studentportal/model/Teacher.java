package io.todimu.practice.studentportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@Entity
@Table(name = "teacher")
@EqualsAndHashCode(callSuper = true)
public class Teacher extends Human {

    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "teacher", allowSetters = true)
    private Set<CourseTeacher> courseTeachers;
}
