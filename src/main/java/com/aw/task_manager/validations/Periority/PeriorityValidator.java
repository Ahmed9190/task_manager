package com.aw.task_manager.validations.Periority;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PeriorityValidator implements ConstraintValidator<Periority, String> {
  List<String> periorities = Arrays.asList("IDLE", "LOW", "MEDIUM", "HIGH");

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null)
      return true;

    for (String periority : periorities) {
      if (value.equals(periority))
        return true;
    }

    return false;
  }

}
