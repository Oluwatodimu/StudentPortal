package io.todimu.practice.studentportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "semester")
@EqualsAndHashCode(callSuper = true)
public class Semester extends BaseEntity {

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToMany(mappedBy = "semester", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "semester", allowSetters = true)
    private Set<CourseRegistration> courseRegistrations;

    @OneToMany(mappedBy = "semester", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "semester", allowSetters = true)
    private Set<CourseTeacher> courseTeachers;
}
