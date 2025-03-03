package com.jastin.department_service.service.impl;

import org.springframework.stereotype.Service;

import com.jastin.department_service.dto.DepartmentDto;
import com.jastin.department_service.entity.Department;
import com.jastin.department_service.repository.DepartmentRepository;
import com.jastin.department_service.service.DepartmentService;

import lombok.AllArgsConstructor;

@Service

public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        // convert department dto to department jpa entity
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode());
        Department savedDepartment = departmentRepository.save(department);
        DepartmentDto savedDepartmentDto = new DepartmentDto(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode());
        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departemntCode) {
        Department department = departmentRepository.findByDepartmentCode(departemntCode);

        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode());

        return departmentDto;
    }

}
