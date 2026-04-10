package com.example.empleado.api.mapper;

import com.example.empleado.api.dto.EmployeeDto;
import com.example.empleado.api.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToemployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDocumentNumber(),
                employee.getDocumentType(),
                employee.getBirthDate(),
                employee.getHireDate(),
                employee.getPosition(),
                employee.getSalary(), null, null
        );
    }

    public static Employee mapToemployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getDocumentType(),
                employeeDto.getDocumentNumber(),
                employeeDto.getBirthDate(),
                employeeDto.getHireDate(),
                employeeDto.getPosition(),
                employeeDto.getSalary()
        );
    }
}




