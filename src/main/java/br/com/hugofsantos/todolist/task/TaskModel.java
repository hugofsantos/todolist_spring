package br.com.hugofsantos.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // JPA
@Entity(name = "tasks")
public class TaskModel {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;    

  private UUID idUser;
  private String description;

  @Column(length = 50) // Máximo de 50 caracteres
  private String title;

  private LocalDateTime startAt;
  private LocalDateTime endAt;
  private String priority;
  
  @CreationTimestamp
  private LocalDateTime createdAt;

}
