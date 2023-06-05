package io.todimu.practice.studentportal.mapper;

import io.todimu.practice.studentportal.dto.StudentDto;
import io.todimu.practice.studentportal.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapper<StudentDto, Student> {
}
