package com.aw.task_manager.dtos.Comment;

import java.time.LocalDateTime;
import java.util.function.Function;

import com.aw.task_manager.entities.Comment;
import com.aw.task_manager.entities.Task;
import com.aw.task_manager.entities.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateCommentDto {
  @NotNull(message = "Text is required")
  @NotBlank(message = "Text can't be empty")
  private String text;

  @NotNull(message = "Task id is required")
  private Integer taskId;

  public Comment toComment(User writtenBy, Function<Integer, Task> findTaskById) {
    LocalDateTime now = LocalDateTime.now();

    return Comment.builder()
        .text(text)
        .writtenBy(writtenBy)
        .task(findTaskById.apply(taskId))
        .createdAt(now)
        .updatedAt(now)
        .build();
  }
}
