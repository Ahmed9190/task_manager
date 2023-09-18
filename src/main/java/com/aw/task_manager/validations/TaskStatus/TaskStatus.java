package com.aw.task_manager.validations.TaskStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TaskStatusValidator.class)
public @interface TaskStatus {
  String message() default "Invalid task status. Allowed values are: TODO, IN_PROGRESS, DONE";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
