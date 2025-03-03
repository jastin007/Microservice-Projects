package com.jastin.employee_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jastin.employee_service.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
