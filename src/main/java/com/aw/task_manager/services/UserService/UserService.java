package com.aw.task_manager.services.UserService;

import com.aw.task_manager.entities.User;

public interface UserService {

  User getUserByUsername(String username);

  User create(User user);

}
