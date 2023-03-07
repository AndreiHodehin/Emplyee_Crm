package com.hodehin.employeeapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(of = {"firstName", "lastName"})
@NoArgsConstructor
public class EmployeeDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String department;
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
    private LocalDate hired;
    private double salary;
    private List<String> tasks;

    public EmployeeDto(Long id, String firstName, String lastName, String department, LocalDate birthDate, String address, String phoneNumber, LocalDate hired, double salary, List<String> tasks) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.hired = hired;
        this.salary = salary;
        this.tasks = tasks;
    }
}
