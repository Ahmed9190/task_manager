package com.aw.task_manager.dtos.Task;

import java.time.LocalDateTime;
import java.util.Set;

import com.aw.task_manager.validations.Periority.Periority;
import com.aw.task_manager.validations.TaskStatus.TaskStatus;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateTaskDto {
  @NotBlank(message = "Title can't be empty")
  private String title;

  @NotBlank(message = "Description can't be empty")
  private String description;

  @Future
  private LocalDateTime dueDate;

  @TaskStatus
  private String status;

  @Periority
  private String periority;

  @Positive
  private Set<Integer> categoryIds;
}
