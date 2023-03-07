package com.hodehin.employeeapp.controller;

import com.hodehin.employeeapp.dto.TaskSimpleDto;
import com.hodehin.employeeapp.dto.TaskDto;
import com.hodehin.employeeapp.model.Task;
import com.hodehin.employeeapp.service.TaskService;
import com.hodehin.employeeapp.utils.Converter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        Task task = taskService.getById(id);
        return ResponseEntity.ok(converter.taskToDto(task));
    }
    @GetMapping
    private ResponseEntity<List<Task>> getAll(){
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
        Task updated = taskService.updateTask(id, task);
        return ResponseEntity.ok(converter.taskToDto(updated));
    }
    @PutMapping("/{id}/{employeeId}")
    public ResponseEntity<TaskDto> setEmplToTask(@PathVariable Long employeeId,
                                           @PathVariable Long id) {
        Task updated = taskService.setEmplToTask(id, employeeId);
        return ResponseEntity.ok(converter.taskToDto(updated));
    }
    @PutMapping("/{id}/start")
    public ResponseEntity<Boolean> startTask(@PathVariable Long id) {
        boolean successful = taskService.startTask(id);
        return ResponseEntity.ok(successful);
    }
    @PutMapping("/{id}/finish")
    public ResponseEntity<Boolean> finishTask(@PathVariable Long id) {
        boolean successful = taskService.finishTask(id);
        return ResponseEntity.ok(successful);
    }

    @GetMapping("/view")
    private ResponseEntity<Page<TaskSimpleDto>> getSimple(Pageable pageable) {
        Page<TaskSimpleDto> dtos = taskService.getAllTasksPageable(pageable);
        return ResponseEntity.ok(dtos);
    }
}
