package com.aw.task_manager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aw.task_manager.dtos.Comment.CreateCommentDto;
import com.aw.task_manager.dtos.Comment.UpdateCommentDto;
import com.aw.task_manager.entities.Comment;
import com.aw.task_manager.services.CommentService.CommentService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {
  private final CommentService commentService;

  @GetMapping
  public ResponseEntity<List<Comment>> findAll() {
    List<Comment> comments = commentService.findAll();
    return ResponseEntity.ok(comments);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Comment> findOne(@PathVariable Integer id) {
    Comment comment = commentService.findById(id);
    return ResponseEntity.ok(comment);
  }

  @PostMapping
  public ResponseEntity<Comment> create(@RequestBody CreateCommentDto createCommentDto) {
    Comment createdCommentDto = commentService.create(createCommentDto);

    return new ResponseEntity<>(createdCommentDto, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Comment> update(
      @PathVariable Integer id,
      @RequestBody UpdateCommentDto updateCommentDto) {
    Comment updatedCommentDto = commentService.update(id, updateCommentDto);

    return ResponseEntity.ok(updatedCommentDto);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Comment> delete(
      @PathVariable Integer id,
      @RequestBody CreateCommentDto comment) {
    commentService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
