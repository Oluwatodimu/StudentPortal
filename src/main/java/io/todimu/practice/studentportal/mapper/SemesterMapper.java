package io.todimu.practice.studentportal.mapper;

import io.todimu.practice.studentportal.dto.SemesterDto;
import io.todimu.practice.studentportal.model.Semester;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SemesterMapper extends EntityMapper<SemesterDto, Semester> {
}
