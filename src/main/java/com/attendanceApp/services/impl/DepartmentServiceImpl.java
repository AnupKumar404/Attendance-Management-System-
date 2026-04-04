package com.attendanceApp.services.impl;

import com.attendanceApp.dtos.DepartmentDto;
import com.attendanceApp.entities.Department;
import com.attendanceApp.exceptions.DuplicateResourceException;
import com.attendanceApp.repositories.DepartmentRepository;
import com.attendanceApp.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public DepartmentDto addDepartment(DepartmentDto data) {
        
        if(departmentRepository.existsByName(data.name())){
            throw new DuplicateResourceException("already exists");
        }

        Department department = Department.builder()
                .name(data.name())
                .headOfDepartment(data.headOfDepartment())
                .build();

        departmentRepository.save(department);
        return modelMapper.map(department, DepartmentDto.class);
    }
}