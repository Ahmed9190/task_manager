package com.aw.task_manager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Comment extends BaseEntityWithTimestamps {
  @Column(nullable = false)
  private String text;

  @JsonIgnore
  @ManyToOne(optional = false)
  @JoinColumn(name = "written_by", referencedColumnName = "id")
  private User writtenBy;

  @JsonIgnore
  @ManyToOne(optional = false)
  @JoinColumn(name = "task_id", referencedColumnName = "id")
  private Task task;
}
