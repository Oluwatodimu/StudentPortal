package io.todimu.practice.studentportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "course_teacher")
public class CourseTeacher extends BaseEntity {

}
