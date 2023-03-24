package io.todimu.practice.studentportal.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "course_grade")
public class CourseGrade extends BaseEntity {

}
