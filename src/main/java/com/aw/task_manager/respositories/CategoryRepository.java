package com.aw.task_manager.respositories;

import org.springframework.data.repository.CrudRepository;

import com.aw.task_manager.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
