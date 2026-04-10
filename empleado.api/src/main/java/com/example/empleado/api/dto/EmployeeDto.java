package com.example.empleado.api.dto;

import com.example.empleado.api.entity.Employee;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String documentType;
    private LocalDate birthDate;
    private LocalDate hireDate;
    private String position;
    private double salary;
    private String currentAge;
    private String companyTime;
}

