package com.aw.task_manager.validations.Periority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PeriorityValidator.class)
public @interface Periority {
  String message() default "Invalid task periority. Allowed values are: IDLE, LOW, MEDIUM, HIGH";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
