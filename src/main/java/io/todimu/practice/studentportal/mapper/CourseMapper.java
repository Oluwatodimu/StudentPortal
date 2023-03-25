package io.todimu.practice.studentportal.mapper;

import io.todimu.practice.studentportal.dto.CourseDto;
import io.todimu.practice.studentportal.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper extends EntityMapper<CourseDto, Course> {

}
