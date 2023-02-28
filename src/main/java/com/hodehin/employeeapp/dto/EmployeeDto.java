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
@AllArgsConstructor
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
    private List<TaskDto> tasks;

}
