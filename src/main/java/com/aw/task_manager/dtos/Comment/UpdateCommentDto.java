package com.aw.task_manager.dtos.Comment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateCommentDto {
  @NotNull(message = "Text is required")
  private String text;

}
