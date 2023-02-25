package com.hodehin.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hodehin.employeeapp.model.embedded.EmployeeDetail;
import com.hodehin.employeeapp.model.embedded.EmployeePersonalInfo;
import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Embedded
    private EmployeeDetail employeeDetail;
    @Embedded
    private EmployeePersonalInfo personalInfo;
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;


    @ManyToMany(mappedBy = "employees",fetch = FetchType.LAZY)
    @JsonIgnore
    @Setter(AccessLevel.PRIVATE)
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        addTask(task,false);
    }
    public void addTask(Task task, boolean setFromAnotherSide){
        this.getTasks().add(task);
        if(setFromAnotherSide){
            return;
        }
        task.addEmployee(this,true);
    }

}
