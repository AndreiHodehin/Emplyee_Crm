package com.hodehin.employeeapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private Long id;
    private String description;
    private LocalDateTime startTime;
    private Boolean completed;
    private Integer estimate;
    private List<Long> employees;
}
