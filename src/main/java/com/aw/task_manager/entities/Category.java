package com.aw.task_manager.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Category extends BaseEntity {
  private String name;

  @JsonIgnore
  @ManyToMany
  @Builder.Default
  private Set<Task> tasks = new HashSet<>();

  @JsonIgnore
  @ManyToOne(optional = false)
  @JoinColumn(name = "created_by", referencedColumnName = "id")
  private User createdBy;

  public static Set<Category> categoriesFromCategoryIds(Set<Integer> categoryIds) {
    return categoryIds.stream()
        .map((categoryId) -> Category.builder().id(categoryId).build())
        .collect(Collectors.toSet());
  }
}
