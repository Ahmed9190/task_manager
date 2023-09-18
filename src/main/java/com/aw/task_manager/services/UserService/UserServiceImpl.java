package com.aw.task_manager.services.UserService;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aw.task_manager.entities.User;
import com.aw.task_manager.exceptions.EntityNotFoundException;
import com.aw.task_manager.exceptions.UsernameAlreadyExistsException;
import com.aw.task_manager.respositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
  private UserRepository userRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public User getUserByUsername(String username) {
    return unwrapUser(userRepository.findByUsername(username));
  }

  @Override
  public User create(User user) {
    final String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    user.setPassword(hashedPassword);
    try {
      return userRepository.save(user);
    } catch (Exception e) {

      String regexPattern = "username.*already exists.";
      Pattern pattern = Pattern.compile(regexPattern);
      Matcher matcher = pattern.matcher(e.getMessage());

      if (matcher.find()) {
        throw new UsernameAlreadyExistsException(user.getUsername());
      } else {
        throw e;
      }
    }
  }

  private User unwrapUser(Optional<User> optionalUser) {
    return optionalUser.orElseThrow(() -> new EntityNotFoundException(User.class));
  }

}
