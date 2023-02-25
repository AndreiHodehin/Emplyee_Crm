package com.hodehin.employeeapp.model.embedded;

import com.hodehin.employeeapp.enums.Departments;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetail {

    private LocalDate hired;
    @Column(precision = 2)
    private double salary;
}
