package com.hodehin.employeeapp.model;

import com.hodehin.employeeapp.model.embedded.EmployeeDetail;
import com.hodehin.employeeapp.model.embedded.EmployeePersonalInfo;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(mappedBy = "employees",fetch = FetchType.LAZY)
    @Setter(AccessLevel.PRIVATE)
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<ClockInOutEmployee> checkList = new ArrayList<>();

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

    public boolean canCheckIn() {

        return checkList.isEmpty() || checkList.get(checkList.size() - 1).getTimeOut() != null;
    }

    public boolean canCheckOut() {
        if(checkList.isEmpty()) {
            return false;
        }
        ClockInOutEmployee currentRecord = checkList.get(checkList.size()-1);
        return currentRecord.getTimeIn() != null && currentRecord.getTimeOut() == null;
    }
}
