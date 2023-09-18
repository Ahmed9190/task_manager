package com.aw.task_manager.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class Task extends BaseEntityWithTimestamps {
  private String title;

  private String description;

  @Column(name = "due_date")
  private LocalDateTime dueDate;

  private String status;

  @Column(nullable = false)
  private String periority;

  @JsonIgnore
  @ManyToOne(optional = false)
  @JoinColumn(name = "created_by", referencedColumnName = "id")
  private User createdBy;

  @ManyToMany
  @JoinTable(name = "task_category", joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
  @Builder.Default
  private Set<Category> categories = new HashSet<>();

  @OneToMany(mappedBy = "task")
  @Builder.Default
  private List<Comment> comments = new ArrayList<>();

}