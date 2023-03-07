package com.hodehin.employeeapp.dto;

import com.hodehin.employeeapp.enums.Departments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSimpleDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Departments department;
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
    private LocalDate hired;
    private double salary;
}
