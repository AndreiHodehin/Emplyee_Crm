package com.hodehin.employeeapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HireInfoDto {
    private String department;
    private double salary;
}
