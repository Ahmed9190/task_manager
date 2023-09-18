package com.aw.task_manager.services.TaskService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.aw.task_manager.dtos.Task.CreateTaskDto;
import com.aw.task_manager.dtos.Task.UpdateTaskDto;
import com.aw.task_manager.entities.Task;

public interface TaskService {
  List<Task> findAll(String title, LocalDate dueDate);

  Page<Task> findAllPaginated(int page, int pageSize, String title, LocalDate dueDate, String sortField,
      Sort.Direction sortOrder);

  Task findById(Integer id);

  Task create(CreateTaskDto createTaskDto);

  Task update(Integer id, UpdateTaskDto updateTaskDto);

  void delete(Integer id);
}
