package com.hodehin.employeeapp.controller;

import com.hodehin.employeeapp.dto.TaskDto;
import com.hodehin.employeeapp.model.Task;
import com.hodehin.employeeapp.service.TaskService;
import com.hodehin.employeeapp.utils.Converter;
import com.sun.jdi.VoidType;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;
    private Converter converter;
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        TaskDto task = taskService.getById(id);
        return ResponseEntity.ok(task);
    }
    @GetMapping
    private ResponseEntity<List<TaskDto>> getAll(){
        return ResponseEntity.ok(taskService.getAll());
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        Task task = converter.taskToEntity(taskDto);
        taskService.createTask(task);
        taskDto.setId(task.getId());
        return ResponseEntity.ok(taskDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto,
                                           @PathVariable Long id) {
        Task task = converter.taskToEntity(taskDto);
        TaskDto updated = taskService.updateTask(id, task);
        return ResponseEntity.ok(updated);
    }
    @PutMapping("/{id}/{employeeId}")
    public ResponseEntity<TaskDto> setEmplToTask(@PathVariable Long employeeId,
                                           @PathVariable Long id) {
        TaskDto updated = taskService.setEmplToTask(id, employeeId);
        return ResponseEntity.ok(updated);
    }
}
