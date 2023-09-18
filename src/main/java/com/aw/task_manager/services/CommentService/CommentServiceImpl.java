package com.aw.task_manager.services.CommentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.aw.task_manager.Facades.AuthFacade;
import com.aw.task_manager.dtos.Comment.CreateCommentDto;
import com.aw.task_manager.dtos.Comment.UpdateCommentDto;
import com.aw.task_manager.entities.Comment;
import com.aw.task_manager.entities.Task;
import com.aw.task_manager.entities.User;
import com.aw.task_manager.exceptions.EntityNotFoundException;
import com.aw.task_manager.respositories.CommentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
  private final CommentRepository commentRepository;
  private final AuthFacade authFacade;

  @Override
  public List<Comment> findAll() {
    return (List<Comment>) commentRepository.findAll();
  }

  @Override
  public Comment findById(Integer id) {
    final Optional<Comment> optionalComment = commentRepository.findById(id);

    return optionalComment.orElseThrow(() -> new EntityNotFoundException(id, Comment.class));
  }

  @Override
  public Comment create(CreateCommentDto createCommentDto) {
    User writtenBy = authFacade.getAuthenticatedUser();

    final Comment commentToCreate = createCommentDto.toComment(
        writtenBy,
        (Integer taskId) -> Task.builder().id(taskId).build());

    final Comment createdComment = commentRepository.save(commentToCreate);

    return createdComment;
  }

  @Override
  public Comment update(Integer id, UpdateCommentDto updateCommentDto) {
    final Comment commentToUpdate = findById(id);

    if (updateCommentDto.getText() != null)
      commentToUpdate.setText(updateCommentDto.getText());
    commentToUpdate.setUpdatedAt(LocalDateTime.now());

    final Comment updatedComment = commentRepository.save(commentToUpdate);
    return updatedComment;
  }

  @Override
  public void delete(Integer id) {
    commentRepository.deleteById(id);
  }
}
