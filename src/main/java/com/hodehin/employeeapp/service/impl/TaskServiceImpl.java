package com.hodehin.employeeapp.service.impl;

import com.hodehin.employeeapp.dto.TaskDto;
import com.hodehin.employeeapp.exception.EmployeeDoThisTaskException;
import com.hodehin.employeeapp.exception.EmployeeNotFoundException;
import com.hodehin.employeeapp.exception.EmployeeNotHiredException;
import com.hodehin.employeeapp.exception.TaskNotFoundException;
import com.hodehin.employeeapp.model.Employee;
import com.hodehin.employeeapp.model.Task;
import com.hodehin.employeeapp.repositiry.EmployeeRepository;
import com.hodehin.employeeapp.repositiry.TaskRepository;
import com.hodehin.employeeapp.service.TaskService;
import com.hodehin.employeeapp.utils.Converter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository repository;
    private EmployeeRepository employeeRepository;
    private Converter converter;

    @Override
    public TaskDto getById(Long id) {
        Task task = repository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task not found in DB"));
        return converter.taskToDto(task);
    }

    @Override
    public List<TaskDto> getAll() {
        return repository.findAll().stream().map(task -> converter.taskToDto(task)).toList();
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
    public TaskDto updateTask(Long id, Task newTask) {
        Task current = repository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task not found in DB"));
        if(newTask.getCompleted() != null) {
            current.setCompleted(newTask.getCompleted());
        }
        if(newTask.getDescription() != null) {
            current.setDescription(newTask.getDescription());
        }
        if(newTask.getEstimate() != null) {
            current.setEstimate(newTask.getEstimate());
        }
        if(newTask.getStartTime() != null) {
            current.setStartTime(newTask.getStartTime());
        }
        if(newTask.getEmployees() != null) {
            current.getEmployees().addAll(newTask.getEmployees());
        }
        return converter.taskToDto(current);
    }

    @Override
    public TaskDto setEmplToTask(Long id, Long employeeId) {

        Task task = repository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task not found in DB"));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee not present in DB"));
        if(employee.getEmployeeDetail() == null) {
            throw new EmployeeNotHiredException("Employee not hired");
        }
        if(task.getEmployees().contains(employee)){
            throw new EmployeeDoThisTaskException("Employee already setted on this task");
        }
        task.getEmployees().add(employee);
        repository.save(task);
        return converter.taskToDto(task);
    }
}
