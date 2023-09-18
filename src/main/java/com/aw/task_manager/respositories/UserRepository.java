package com.aw.task_manager.respositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.aw.task_manager.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
  Optional<User> findByUsername(String username);
}
