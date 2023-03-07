package com.hodehin.employeeapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskSimpleDto {
    private Long id;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean completed;
    private Integer estimate;

}
