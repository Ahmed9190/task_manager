
package com.aw.task_manager.dtos.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCategoryDto {
  @NotBlank(message = "Name can't be empty")
  private String name;
}
