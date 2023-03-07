package com.hodehin.employeeapp.service;

import com.hodehin.employeeapp.dto.TaskSimpleDto;
import com.hodehin.employeeapp.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    Task getById(Long id);
    List<Task> getAll();

    void createTask(Task task);

    void deleteTask(Long id);

    Task updateTask(Long id, Task task);

    Task setEmplToTask(Long id, Long employeeId);

    boolean startTask(Long id);

    boolean finishTask(Long id);

    Page<TaskSimpleDto> getAllTasksPageable(Pageable pageable);

}
