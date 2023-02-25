package com.hodehin.employeeapp.service.impl;

import com.hodehin.employeeapp.exception.EmployeeNotFoundException;
import com.hodehin.employeeapp.exception.TaskNotFoundException;
import com.hodehin.employeeapp.model.Employee;
import com.hodehin.employeeapp.model.Task;
import com.hodehin.employeeapp.repositiry.EmployeeRepository;
import com.hodehin.employeeapp.repositiry.TaskRepository;
import com.hodehin.employeeapp.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Task createTask(Task task) {
        return repository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Task updateTask(Long id, Task newTask) {
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
//        repository.flush();
        return current;
    }

    @Override
    public Task setEmplToTask(Long id, Long employeeId) {
        System.out.println("in set method");
        Task task = repository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task not found in DB"));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee not present in DB"));
        System.out.println(task);
        System.out.println(employee);
        task.getEmployees().add(employee);
        repository.save(task);
        return task;
    }
}
