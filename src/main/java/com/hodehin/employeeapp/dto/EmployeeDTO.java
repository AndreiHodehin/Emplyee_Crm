package com.hodehin.employeeapp.dto;

import com.hodehin.employeeapp.enums.Departments;
import com.hodehin.employeeapp.model.Department;
import com.hodehin.employeeapp.model.Task;
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
public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
    private LocalDate hired;
    private double salary;
    private List<TaskDto> tasks;

}
