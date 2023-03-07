package com.hodehin.employeeapp.dto;

import com.hodehin.employeeapp.enums.Departments;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentSimpleDto {
    private Long id;
    private Departments name;
    private String location;
}
