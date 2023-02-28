package com.hodehin.employeeapp.service;

import com.hodehin.employeeapp.dto.TaskDto;
import com.hodehin.employeeapp.model.Task;

import java.util.List;

public interface TaskService {
    TaskDto getById(Long id);
    List<TaskDto> getAll();

    void createTask(Task task);

    void deleteTask(Long id);

    TaskDto updateTask(Long id, Task task);

    TaskDto setEmplToTask(Long id, Long employeeId);
}
