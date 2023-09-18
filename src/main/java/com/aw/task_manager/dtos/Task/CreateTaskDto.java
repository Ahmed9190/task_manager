package com.aw.task_manager.dtos.Task;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.aw.task_manager.entities.Category;
import com.aw.task_manager.entities.Task;
import com.aw.task_manager.entities.User;
import com.aw.task_manager.validations.Periority.Periority;
import com.aw.task_manager.validations.TaskStatus.TaskStatus;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateTaskDto {
  @NotNull(message = "Title is required")
  @NotBlank(message = "Title can't be empty")
  private String title;

  @NotNull(message = "Description is required")
  @NotBlank(message = "Description can't be empty")
  private String description;

  @NotNull(message = "Due date is required")
  @Future(message = "Due date must be in the future")
  private LocalDateTime dueDate;

  @NotNull(message = "Status is required")
  @TaskStatus
  private String status;

  @NotNull(message = "Periority is required")
  @Periority
  private String periority = "IDLE";

  @NotNull(message = "Category ids field is required")
  private Set<Integer> categoryIds = new HashSet<>();

  public Task toTask(User createdBy) {
    LocalDateTime now = LocalDateTime.now();
    Task task = Task.builder().title(title)
        .description(description)
        .dueDate(dueDate)
        .status(status)
        .periority(periority)
        .categories(Category.categoriesFromCategoryIds(categoryIds))
        .createdBy(createdBy)
        .createdAt(now)
        .updatedAt(now)
        .build();

    return task;
  }

}
