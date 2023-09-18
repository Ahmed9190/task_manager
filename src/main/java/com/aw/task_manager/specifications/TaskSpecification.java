package com.aw.task_manager.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.aw.task_manager.entities.Task;

public class TaskSpecification {
  public static Specification<Task> withTitle(String title) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),
        "%" + title.toLowerCase() + "%");
  }
}
