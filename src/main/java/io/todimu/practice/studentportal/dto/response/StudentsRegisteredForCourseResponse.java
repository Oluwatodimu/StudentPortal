package io.todimu.practice.studentportal.dto.response;

import io.todimu.practice.studentportal.dto.StudentDto;
import io.todimu.practice.studentportal.dto.TeacherDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentsRegisteredForCourseResponse {

    private String courseCode;

    private String courseName;

    private Set<TeacherDto> courseTeachers;

    private Set<StudentDto> registeredStudents;
}
