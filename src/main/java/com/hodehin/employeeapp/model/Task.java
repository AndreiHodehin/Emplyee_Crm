package com.hodehin.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String description;
    private LocalDateTime startTime;
    @ColumnDefault(value = "false")
    @Column
    private Boolean completed;
    private Integer estimate;
    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employee_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    @JsonIgnore
    private List<Employee> employees = new ArrayList<>();


    public void addEmployee(Employee employee) {
        addEmployee(employee,false);
    }
    public void addEmployee(Employee employee, boolean setFromAnotherSide){
        this.getEmployees().add(employee);
        if(setFromAnotherSide){
            return;
        }
        employee.addTask(this,true);
    }


}
