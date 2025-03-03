package com.jastin.employee_service.service.impl;

import com.jastin.employee_service.service.APIClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.jastin.employee_service.dto.APIResponseDto;
import com.jastin.employee_service.dto.DepartmentDto;
import com.jastin.employee_service.dto.EmployeeDto;
import com.jastin.employee_service.entity.Employee;
import com.jastin.employee_service.repository.EmployeeRepository;
import com.jastin.employee_service.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service

public class EmployeeServiceImpl implements EmployeeService {

        private EmployeeRepository employeeRepository;
//        private RestTemplate restTemplate;
        // private WebClient webClient;
        private APIClient apiClient;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, APIClient apiClient) {
        this.employeeRepository = employeeRepository;
        this.apiClient = apiClient;
    }

    @Override
        public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
                Employee employee = new Employee(
                                employeeDto.getId(),
                                employeeDto.getFirstName(),
                                employeeDto.getLastName(),
                                employeeDto.getEmail(),
                                employeeDto.getDepartmentCode());
                Employee savedEmployee = employeeRepository.save(employee);
                EmployeeDto savedEmployeeDto = new EmployeeDto(
                                savedEmployee.getId(),
                                savedEmployee.getFirstName(),
                                savedEmployee.getLastName(),
                                savedEmployee.getEmail(),
                                savedEmployee.getDepartmentCode());
                return savedEmployeeDto;
        }

        @Override
        public APIResponseDto getEmployeeById(Long employeeId) {
                Employee employee = employeeRepository.findById(employeeId).get();

//                ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
//                                "http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//                                DepartmentDto.class);
//                DepartmentDto departmentDto = responseEntity.getBody();
                // DepartmentDto departmentDto = webClient.get()
                // .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                // .retrieve()
                // .bodyToMono(DepartmentDto.class)
                // .block();
               DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
                EmployeeDto employeeDto = new EmployeeDto(
                                employee.getId(),
                                employee.getFirstName(),
                                employee.getLastName(),
                                employee.getEmail(),
                                employee.getDepartmentCode());

                APIResponseDto apiResponseDto = new APIResponseDto();
                apiResponseDto.setEmployee(employeeDto);
                apiResponseDto.setDepartment(departmentDto);
                return apiResponseDto;
        }

}
