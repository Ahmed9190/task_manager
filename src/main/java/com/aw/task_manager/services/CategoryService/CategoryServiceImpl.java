package com.aw.task_manager.services.CategoryService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aw.task_manager.dtos.Category.CreateCategoryDto;
import com.aw.task_manager.dtos.Category.UpdateCategoryDto;
import com.aw.task_manager.entities.Category;
import com.aw.task_manager.exceptions.EntityNotFoundException;
import com.aw.task_manager.respositories.CategoryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;

  @Override
  public List<Category> findAll() {
    return (List<Category>) categoryRepository.findAll();
  }

  @Override
  public Category findById(Integer id) {
    final Optional<Category> optionalCategory = categoryRepository.findById(id);

    return optionalCategory.orElseThrow(() -> new EntityNotFoundException(id, Category.class));
  }

  @Override
  public Category create(CreateCategoryDto createCategoryDto) {
    final Category createdCategory = categoryRepository.save(createCategoryDto.toCategory());

    return createdCategory;
  }

  @Override
  public Category update(Integer id, UpdateCategoryDto updateCategoryDto) {
    final Category category = findById(id);

    if (updateCategoryDto.getName() != null)
      category.setName(updateCategoryDto.getName());

    final Category updatedCategory = categoryRepository.save(category);

    return updatedCategory;
  }

  @Override
  public void delete(Integer id) {
    categoryRepository.deleteById(id);
  }

}
