package com.aw.task_manager.dtos.Category;

import com.aw.task_manager.entities.Category;
import com.aw.task_manager.entities.User;
import com.aw.task_manager.entities.Category.CategoryBuilder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Setter
public class CreateCategoryDto {
  @NotNull(message = "Name is required")
  @NotBlank(message = "Name can't be empty")
  private String name;

  private User createdBy;

  public Category toCategory() {
    CategoryBuilder<?, ?> categoryBuilder = Category.builder();

    Category category = categoryBuilder
        .name(name)
        .createdBy(createdBy)
        .build();

    return category;
  }
}
