package com.example.empleado.api.service.impl;

import com.example.empleado.api.dto.EmployeeDto;
import com.example.empleado.api.entity.Employee;
import com.example.empleado.api.exception.ResourceNotFoundException;
import com.example.empleado.api.mapper.EmployeeMapper;
import com.example.empleado.api.repository.EmployeeRepository;
import com.example.empleado.api.service.Employeeservice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Service
@AllArgsConstructor
public class EmployeeServicelmpl implements Employeeservice {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToemployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToemployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
        return EmployeeMapper.mapToemployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> EmployeeMapper.mapToemployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(long employeId, EmployeeDto updateEmployee) {
        Employee employee = employeeRepository.findById(employeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + employeId
                ));

        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setDocumentType(updateEmployee.getDocumentType());
        employee.setDocumentNumber(updateEmployee.getDocumentNumber());
        employee.setBirthDate(updateEmployee.getBirthDate());
        employee.setHireDate(updateEmployee.getHireDate());
        employee.setPosition(updateEmployee.getPosition());
        employee.setSalary(updateEmployee.getSalary());

        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToemployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with id: " + employeeId)
        );
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public EmployeeDto registerAndEnrich(EmployeeDto employeeDto) {
        // Save employee in database
        Employee employee = EmployeeMapper.mapToemployee(employeeDto);
        Employee saved = employeeRepository.save(employee);
        EmployeeDto result = EmployeeMapper.mapToemployeeDto(saved);

        // Calculate current age
        Period age = Period.between(saved.getBirthDate(), LocalDate.now());
        result.setCurrentAge(age.getYears() + " years, " + age.getMonths() + " months, " + age.getDays() + " days");

        // Calculate company tenure
        Period tenure = Period.between(saved.getHireDate(), LocalDate.now());
        result.setCompanyTime(tenure.getYears() + " years, " + tenure.getMonths() + " months, " + tenure.getDays() + " days");

        return result;
    }
}


