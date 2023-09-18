package com.aw.task_manager.services.CommentService;

import java.util.List;

import com.aw.task_manager.dtos.Comment.CreateCommentDto;
import com.aw.task_manager.dtos.Comment.UpdateCommentDto;
import com.aw.task_manager.entities.Comment;

public interface CommentService {

  List<Comment> findAll();

  Comment findById(Integer id);

  Comment create(CreateCommentDto createCommentDto);

  Comment update(Integer id, UpdateCommentDto updateCommentDto);

  void delete(Integer id);

}
