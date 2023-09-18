package com.aw.task_manager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aw.task_manager.dtos.User.RegisterUserDto;
import com.aw.task_manager.services.UserService.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {
  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<HttpStatus> register(@Valid @RequestBody RegisterUserDto userDto) {
    userService.create(userDto.toUser());
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
