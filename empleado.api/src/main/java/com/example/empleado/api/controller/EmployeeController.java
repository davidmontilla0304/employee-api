package com.example.empleado.api.controller;

import com.example.empleado.api.dto.EmployeeDto;
import com.example.empleado.api.service.Employeeservice;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
@AllArgsConstructor
@RestController
@RequestMapping("/api/employee")

public class EmployeeController {
    private Employeeservice employeeservice;
    // Build add employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeservice.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    //build get employee rest api
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeByid(@PathVariable("id") Long employeeId){
     EmployeeDto employeeDto = employeeservice.getEmployeeById(employeeId);
     return ResponseEntity.ok(employeeDto);
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeservice.getAllEmployees();
        return ResponseEntity.ok(employees);

    }
    // Build Update Employee REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,
                                                      @RequestBody EmployeeDto updatedEmployee){
        EmployeeDto employeeDto = employeeservice.updateEmployee(employeeId, updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }
    // Build Delete Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeservice.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully!");
    }
    // Endpoint: POST with validations
    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody EmployeeDto employeeDto) {

        // Validate empty fields
        if (employeeDto.getFirstName() == null || employeeDto.getFirstName().isBlank() ||
                employeeDto.getLastName() == null || employeeDto.getLastName().isBlank() ||
                employeeDto.getDocumentType() == null || employeeDto.getDocumentType().isBlank() ||
                employeeDto.getDocumentNumber() == null || employeeDto.getDocumentNumber().isBlank() ||
                employeeDto.getPosition() == null || employeeDto.getPosition().isBlank() ||
                employeeDto.getBirthDate() == null || employeeDto.getHireDate() == null) {
            return ResponseEntity.badRequest().body("All fields are required");
        }
        // Validate employee is of legal age
        if (Period.between(employeeDto.getBirthDate(), LocalDate.now()).getYears() < 18) {
            return ResponseEntity.badRequest().body("Employee must be of legal age (minimum 18 years)");
        }

        EmployeeDto result = employeeservice.registerAndEnrich(employeeDto);
        return ResponseEntity.ok(result);
    }
}

