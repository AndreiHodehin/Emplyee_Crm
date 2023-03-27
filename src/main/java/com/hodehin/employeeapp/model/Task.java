package com.hodehin.employeeapp.model;


import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(of = "description")
@RequiredArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ColumnDefault(value = "false")
    @Column
    @Setter(AccessLevel.PRIVATE)
    private Boolean completed;
    private Integer estimate;
    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employee_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
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


    public void completeTask() {
        this.completed = true;
    }
}
