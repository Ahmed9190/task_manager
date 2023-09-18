package com.aw.task_manager.services.TaskService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.aw.task_manager.Facades.AuthFacade;
import com.aw.task_manager.dtos.Task.CreateTaskDto;
import com.aw.task_manager.dtos.Task.UpdateTaskDto;
import com.aw.task_manager.entities.Category;
import com.aw.task_manager.entities.Task;
import com.aw.task_manager.entities.User;
import com.aw.task_manager.exceptions.EntityNotFoundException;
import com.aw.task_manager.respositories.TaskRepository;
import com.aw.task_manager.specifications.TaskSpecification;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
  private final TaskRepository taskRepository;
  private final AuthFacade authFacade;

  @Override
  public List<Task> findAll(String title, LocalDate dueDate) {
    Specification<Task> spec = getTaskSpecification(title, dueDate);

    final List<Task> tasks = (List<Task>) taskRepository.findAll(spec);
    return tasks;
  }

  @Override
  public Page<Task> findAllPaginated(int page, int pageSize, String title, LocalDate dueDate, String sortField,
      Sort.Direction sortOrder) {
    PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(sortOrder, sortField));

    Specification<Task> spec = getTaskSpecification(title, dueDate);

    final Page<Task> taskPage = taskRepository.findAll(spec, pageRequest);
    final List<Task> tasks = taskPage.getContent();

    return new PageImpl<>(tasks, pageRequest, taskPage.getTotalElements());
  }

  private Specification<Task> getTaskSpecification(String title, LocalDate dueDate) {
    Specification<Task> spec = Specification.where(null);

    if (title != null && !title.isEmpty()) {
      spec = spec.and(TaskSpecification.withTitle(title));
    }

    return spec;
  }

  @Override
  public Task findById(Integer id) {
    final Optional<Task> optionalTask = taskRepository.findById(id);

    return optionalTask.orElseThrow(() -> new EntityNotFoundException(id, Task.class));
  }

  @Override
  public Task create(CreateTaskDto createTaskDto) {
    User createdBy = authFacade.getAuthenticatedUser();

    final Task taskToCreate = createTaskDto.toTask(createdBy);
    final Task createdTask = taskRepository.save(taskToCreate);
    return createdTask;
  }

  @Override
  public Task update(Integer id, UpdateTaskDto updateTaskDto) {
    final Task taskToUpdate = findById(id);

    if (updateTaskDto.getTitle() != null)
      taskToUpdate.setTitle(updateTaskDto.getTitle());
    if (updateTaskDto.getDescription() != null)
      taskToUpdate.setDescription(updateTaskDto.getDescription());
    if (updateTaskDto.getDueDate() != null)
      taskToUpdate.setDueDate(updateTaskDto.getDueDate());
    if (updateTaskDto.getStatus() != null)
      taskToUpdate.setStatus(updateTaskDto.getStatus());
    if (updateTaskDto.getPeriority() != null)
      taskToUpdate.setPeriority(updateTaskDto.getPeriority());
    if (updateTaskDto.getCategoryIds() != null) {
      final Set<Category> categories = Category.categoriesFromCategoryIds(updateTaskDto.getCategoryIds());
      taskToUpdate.setCategories(categories);
    }
    taskToUpdate.setUpdatedAt(LocalDateTime.now());

    final Task updatedTask = taskRepository.save(taskToUpdate);
    return updatedTask;
  }

  @Override
  public void delete(Integer id) {
    taskRepository.deleteById(id);
  }

}
