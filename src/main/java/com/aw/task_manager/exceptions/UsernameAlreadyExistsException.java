package com.aw.task_manager.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
  public UsernameAlreadyExistsException(String username) {
    super("Username '" + username + "' is already in use");
  }
}
