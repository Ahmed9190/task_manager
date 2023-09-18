package com.aw.task_manager.Facades;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.aw.task_manager.entities.User;
import com.aw.task_manager.services.UserService.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class AuthFacade {
  private final UserService userService;

  public String getUsernameOfAuthenticatedUser() {
    final var userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = userDetails.toString();
    return username;
  }

  public User getAuthenticatedUser() {
    String username = getUsernameOfAuthenticatedUser();
    return userService.getUserByUsername(username);
  }
}
