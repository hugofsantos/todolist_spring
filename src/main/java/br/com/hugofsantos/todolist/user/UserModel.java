package br.com.hugofsantos.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Adicionar getters e setters
@NoArgsConstructor // JPA
@Entity(name = "tb_users")
public class UserModel {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @Column(unique = true)
  private String username;
  
  private String password;
  private String name;

  @CreationTimestamp
  private LocalDateTime createdAt;

  public UserModel(String name, String username, String password) {
    this.name = name;
    this.username = username;
    this.password = password;
  }
}
