package com.aw.task_manager.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aw.task_manager.Facades.AuthFacade;
import com.aw.task_manager.dtos.Category.CreateCategoryDto;
import com.aw.task_manager.dtos.Category.UpdateCategoryDto;
import com.aw.task_manager.entities.Category;
import com.aw.task_manager.entities.User;
import com.aw.task_manager.services.CategoryService.CategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
  private final CategoryService categoryService;
  private final AuthFacade authFacade;

  @GetMapping
  public ResponseEntity<List<Category>> findAll() {
    List<Category> categories = categoryService.findAll();

    return ResponseEntity.ok(categories);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> findOne(@PathVariable Integer id) {
    Category category = categoryService.findById(id);

    return ResponseEntity.ok(category);
  }

  @PostMapping
  public ResponseEntity<Category> create(@Valid @RequestBody CreateCategoryDto createCategoryDto) {
    User createdBy = authFacade.getAuthenticatedUser();
    createCategoryDto.setCreatedBy(createdBy);

    Category category = categoryService.create(createCategoryDto);

    return new ResponseEntity<>(category, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Category> update(
      @PathVariable Integer id,
      @Valid @RequestBody UpdateCategoryDto updateCategoryDto) {
    System.out.println(updateCategoryDto.toString());
    Category category = categoryService.update(id, updateCategoryDto);

    return ResponseEntity.ok(category);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    categoryService.delete(id);

    return ResponseEntity.noContent().build();
  }
}
