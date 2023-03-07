package com.hodehin.employeeapp.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;


@Data
@Component
public class EmployeeCheckListDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    @Value("${start}")
    private double startWorkingDay;
    @Value("${hours}")
    private long workingHours;
    private Boolean okWorkingHours;
    private Boolean beOnTime;

    public EmployeeCheckListDto(){

    }

    public EmployeeCheckListDto(Long id, String firstName, String lastName, LocalDateTime timeIn, LocalDateTime timeOut) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        checkWorkingHours();
        checkBeingOnTime();
    }

    private void checkWorkingHours() {
        if(timeIn != null && timeOut != null){
        Duration durationFact = Duration.between(timeIn,timeOut);
        okWorkingHours = !durationFact.isNegative() && durationFact.toHours()>=workingHours;
        } else {
            okWorkingHours = null;
        }
    }
    private void checkBeingOnTime() {
        if(timeIn != null) {
            beOnTime = timeIn.getHour() <= startWorkingDay;
        } else {
            beOnTime = null;
        }
    }

}
