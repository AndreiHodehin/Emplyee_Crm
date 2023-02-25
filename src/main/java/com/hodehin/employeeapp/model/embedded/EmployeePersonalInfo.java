package com.hodehin.employeeapp.model.embedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePersonalInfo {
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
}
