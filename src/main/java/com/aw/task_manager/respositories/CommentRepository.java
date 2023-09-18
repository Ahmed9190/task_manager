package com.aw.task_manager.respositories;

import org.springframework.data.repository.CrudRepository;

import com.aw.task_manager.entities.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

}
