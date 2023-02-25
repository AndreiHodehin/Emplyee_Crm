package com.hodehin.employeeapp.service;

import com.hodehin.employeeapp.model.Task;

import java.util.List;

public interface TaskService {
    Task getById(Long id);
    List<Task> getAll();

    Task createTask(Task task);

    void deleteTask(Long id);

    Task updateTask(Long id, Task task);

    Task setEmplToTask(Long id, Long employeeId);
}
