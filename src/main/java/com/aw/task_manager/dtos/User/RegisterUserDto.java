package com.aw.task_manager.dtos.User;

import com.aw.task_manager.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterUserDto {
  @NotNull(message = "Username is required")
  @NotBlank(message = "Password can't be empty")
  final private String username;

  @NotNull(message = "Email is required")
  @NotBlank(message = "Email can't be empty")
  final private String email;

  @NotNull(message = "Password is required")
  @NotNull(message = "Password can't be empty")
  final private String password;

  public User toUser() {
    final User user = new User();
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(password);

    return user;
  }
}
