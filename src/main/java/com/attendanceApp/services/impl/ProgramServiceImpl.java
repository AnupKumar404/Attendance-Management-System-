package com.attendanceApp.services.impl;

import com.attendanceApp.dtos.ProgramDto;
import com.attendanceApp.entities.Department;
import com.attendanceApp.entities.Program;
import com.attendanceApp.enums.ProgramType;
import com.attendanceApp.exceptions.ResourceNotFoundException;
import com.attendanceApp.repositories.DepartmentRepository;
import com.attendanceApp.repositories.ProgramRepository;
import com.attendanceApp.services.ProgramService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProgramDto createProgram(ProgramDto programDto) {

//        if(programRepository.existsByDepartmentIdAndName(programDto.departmentId()))

        Department department = departmentRepository.findById(programDto.departmentId())
                .orElseThrow(() -> new ResourceNotFoundException("department not found"));


        Program program = Program.builder()
                .name(ProgramType.BTech)
                .duration(programDto.duration())
                .totalSemesters(programDto.totalSemesters())
                .build();

        program.setDepartment(department);

        programRepository.save(program);
        return modelMapper.map(program, ProgramDto.class);
    }
}