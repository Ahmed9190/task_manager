package com.aw.task_manager.validations.TaskStatus;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TaskStatusValidator implements ConstraintValidator<TaskStatus, String> {
  List<String> statusValues = Arrays.asList("TODO", "IN_PROGRESS", "DONE");

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null)
      return true;

    for (String statusValue : statusValues) {
      if (value.equals(statusValue))
        return true;
    }

    return false;
  }

}
