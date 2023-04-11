package io.todimu.practice.studentportal.mapper;

import io.todimu.practice.studentportal.dto.TeacherDto;
import io.todimu.practice.studentportal.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper extends EntityMapper<TeacherDto, Teacher> {
}
