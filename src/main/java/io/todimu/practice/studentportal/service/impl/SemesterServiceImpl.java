package io.todimu.practice.studentportal.service.impl;

import io.todimu.practice.studentportal.dto.SemesterDto;
import io.todimu.practice.studentportal.dto.request.CreateSemesterRequest;
import io.todimu.practice.studentportal.mapper.SemesterMapper;
import io.todimu.practice.studentportal.model.Semester;
import io.todimu.practice.studentportal.repository.SemesterRepository;
import io.todimu.practice.studentportal.service.SemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SemesterServiceImpl implements SemesterService {

    private final SemesterRepository semesterRepository;

    private final SemesterMapper semesterMapper;

    public SemesterDto createSemester(CreateSemesterRequest createSemesterRequest) {
        SemesterDto semesterDto = SemesterDto.builder()
                .startDate(createSemesterRequest.getStartDate())
                .endDate(createSemesterRequest.getEndDate())
                .build();

        semesterDto = saveSemesterDto(semesterDto);
        return semesterDto;
    }

    public SemesterDto findSemesterById(Long id) {
        Optional<Semester> semesterOptional = semesterRepository.findById(id);
        if (semesterOptional.isEmpty()) {
            throw new RuntimeException("semester not found");
        }

        Semester semester = semesterOptional.get();
        return semesterMapper.toDto(semester);
    }

    public List<SemesterDto> findAllSemesters() {
        List<Semester> semesterList =  semesterRepository.findAll();
        return semesterList.stream()
                .map(semesterMapper::toDto)
                .collect(Collectors.toList());
    }

    private SemesterDto saveSemesterDto(SemesterDto semesterDto) {
        Semester semester = semesterMapper.toEntity(semesterDto);
        semester = semesterRepository.save(semester);
        return semesterMapper.toDto(semester);
    }

}
