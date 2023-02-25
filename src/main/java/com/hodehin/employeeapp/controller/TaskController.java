package com.hodehin.employeeapp.controller;

import com.hodehin.employeeapp.model.Task;
import com.hodehin.employeeapp.service.TaskService;
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

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        return ResponseEntity.ok(task);
    }
    @GetMapping
    private ResponseEntity<List<Task>> getAll(){
        return ResponseEntity.ok(taskService.getAll());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task created = taskService.createTask(task);
        return ResponseEntity.ok(created);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody Task task,
                                           @PathVariable Long id) {
        Task updated = taskService.updateTask(id, task);
        return ResponseEntity.ok(updated);
    }
    @PutMapping("/{id}/{employeeId}")
    public ResponseEntity<Task> setEmplToTask(@PathVariable Long employeeId,
                                           @PathVariable Long id) {
        Task updated = taskService.setEmplToTask(id, employeeId);
        return ResponseEntity.ok(updated);
    }
}
