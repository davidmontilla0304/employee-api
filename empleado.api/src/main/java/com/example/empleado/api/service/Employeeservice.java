package com.example.empleado.api.service;

import com.example.empleado.api.dto.EmployeeDto;

import java.util.List;

public interface Employeeservice {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeeId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(long employeId, EmployeeDto updateEmployee);
   void deleteEmployee(Long employeId);
    EmployeeDto registerAndEnrich(EmployeeDto employeeDto);
}
