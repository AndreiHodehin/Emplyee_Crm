package com.hodehin.employeeapp.dto;

import com.hodehin.employeeapp.enums.Departments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Long id;
    private Departments name;
    private String location;
    private List<Long> employees;
}
