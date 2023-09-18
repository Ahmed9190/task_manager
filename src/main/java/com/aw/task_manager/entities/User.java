package com.aw.task_manager.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "users")
public class User extends BaseEntity {
  @Column(unique = true, nullable = false)
  private String username;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @JsonIgnore
  @OneToMany(mappedBy = "createdBy")
  private List<Task> tasks;

  @JsonIgnore
  @OneToMany(mappedBy = "createdBy")
  private List<Category> categories;

  @JsonIgnore
  @OneToMany(mappedBy = "writtenBy")
  private List<Comment> comments;
}
