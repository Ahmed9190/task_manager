package com.aw.task_manager.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aw.task_manager.dtos.Task.CreateTaskDto;
import com.aw.task_manager.dtos.Task.UpdateTaskDto;
import com.aw.task_manager.entities.Task;
import com.aw.task_manager.services.TaskService.TaskService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {
  private final TaskService taskService;

  @GetMapping
  public ResponseEntity<List<Task>> findAll(
      @RequestParam(name = "title", required = false) String title,
      @RequestParam(name = "dueDate", required = false) LocalDate dueDate) {
    List<Task> tasks = taskService.findAll(title, dueDate);
    return ResponseEntity.ok(tasks);
  }

  @GetMapping("/paginated")
  public ResponseEntity<Page<Task>> findAllPaginated(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
      @RequestParam(name = "title", required = false) String title,
      @RequestParam(name = "dueDate", required = false) LocalDate dueDate,
      @RequestParam(name = "sortField", defaultValue = "id") String sortField,
      @RequestParam(name = "sortOrder", defaultValue = "DESC") Sort.Direction sortOrder) {
    Page<Task> tasks = taskService.findAllPaginated(page, pageSize, title, dueDate, sortField, sortOrder);
    return ResponseEntity.ok(tasks);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Task> findOne(@PathVariable Integer id) {
    Task task = taskService.findById(id);
    return ResponseEntity.ok(task);
  }

  @PostMapping
  public ResponseEntity<Task> create(@Valid @RequestBody CreateTaskDto createTaskDto) {
    Task createdTaskDto = taskService.create(createTaskDto);

    return new ResponseEntity<>(createdTaskDto, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Task> update(
      @PathVariable Integer id,
      @Valid @RequestBody UpdateTaskDto updateTaskDto) {
    Task updatedTaskDto = taskService.update(id, updateTaskDto);

    return ResponseEntity.ok(updatedTaskDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    taskService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
