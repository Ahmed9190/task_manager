package com.aw.task_manager.exceptions;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(Class<?> entity) {
    super("The " + entity.getSimpleName().toLowerCase() + " does not exist in our records");
  }

  public EntityNotFoundException(Integer id, Class<?> entity) {
    super("The " + entity.getSimpleName().toLowerCase() + " with id '" + id + "' does not exist in our records");
  }
}
