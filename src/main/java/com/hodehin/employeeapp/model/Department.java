package com.hodehin.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hodehin.employeeapp.enums.Departments;
import jakarta.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(of = "name")
public class Department {

    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private Departments name;
    private String location;
    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Employee> employees;

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setDepartment(this);
    }
}
