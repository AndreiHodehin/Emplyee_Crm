package com.hodehin.employeeapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeInfoDto {
    private String phoneNumber;
    private String address;
    private LocalDate birthDate;
}
