package com.hodehin.employeeapp.service.impl;

import com.hodehin.employeeapp.dto.TaskSimpleDto;
import com.hodehin.employeeapp.exception.*;
import com.hodehin.employeeapp.model.Employee;
import com.hodehin.employeeapp.model.Task;
import com.hodehin.employeeapp.repositiry.EmployeeRepository;
import com.hodehin.employeeapp.repositiry.TaskRepository;
import com.hodehin.employeeapp.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository repository;
    private EmployeeRepository employeeRepository;


    @Override
    public Task getById(Long id) {
        return repository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task not found in DB"));
    }

    @Override
    public List<Task> getAll() {
        return repository.findAll();
    }

    @Override
    public void createTask(Task task) {
        repository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Task updateTask(Long id, Task newTask) {
        Task current = repository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task not found in DB"));
        if(newTask.getDescription() != null) {
            current.setDescription(newTask.getDescription());
        }
        if(newTask.getEstimate() != null) {
            current.setEstimate(newTask.getEstimate());
        }
        if(newTask.getStartTime() != null) {
            current.setStartTime(newTask.getStartTime());
        }
        if(newTask.getEndTime() != null) {
            current.setEndTime(newTask.getEndTime());
        }
        if(newTask.getEmployees() != null) {
            current.getEmployees().addAll(newTask.getEmployees());
        }
        return current;
    }

    @Override
    public Task setEmplToTask(Long id, Long employeeId) {

        Task task = repository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task not found in DB"));
        if(task.getStartTime() != null) {
            throw new TaskAlreadyStartedException("Cannot set employee to task because it in progress");
        }
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee not present in DB"));
        if(employee.getEmployeeDetail() == null) {
            throw new EmployeeNotHiredException("Employee not hired");
        }
        if(task.getEmployees().contains(employee)){
            throw new EmployeeDoThisTaskException("Employee already serted on this task");
        }
        task.getEmployees().add(employee);
        repository.save(task);
        return task;
    }

    @Override
    public boolean startTask(Long id) {
        Task task = repository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task not found in DB"));
        if(task.getEmployees().isEmpty() || task.getCompleted() || task.getStartTime() != null) {
            return false;
        }
        task.setStartTime(LocalDateTime.now());
        return true;
    }

    @Override
    public boolean finishTask(Long id) {
        Task task = repository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task not found in DB"));
        if(task.getCompleted() || task.getStartTime() == null || task.getEndTime() != null) {
            return false;
        }
        task.setEndTime(LocalDateTime.now());
        task.completeTask();
        return true;
    }

    @Override
    public Page<TaskSimpleDto> getAllTasksPageable(Pageable pageable) {
        return repository.findAllBy(TaskSimpleDto.class,pageable);
    }
}
