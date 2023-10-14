package br.com.hugofsantos.todolist.user;

import lombok.Data;

@Data // Adicionar getters e setters
public class UserModel {
  private String username;
  private String password;
  private String name;
}
