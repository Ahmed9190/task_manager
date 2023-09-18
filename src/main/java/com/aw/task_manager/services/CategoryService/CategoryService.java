package com.aw.task_manager.services.CategoryService;

import java.util.List;

import com.aw.task_manager.dtos.Category.CreateCategoryDto;
import com.aw.task_manager.dtos.Category.UpdateCategoryDto;
import com.aw.task_manager.entities.Category;

public interface CategoryService {
  List<Category> findAll();

  Category findById(Integer id);

  Category create(CreateCategoryDto createCategoryDto);

  Category update(Integer id, UpdateCategoryDto updateCategoryDto);

  void delete(Integer id);
}
