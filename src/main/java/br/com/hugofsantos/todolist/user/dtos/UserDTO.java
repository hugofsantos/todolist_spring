package br.com.hugofsantos.todolist.user.dtos;

import br.com.hugofsantos.todolist.user.UserModel;
import lombok.Getter;

@Getter
public class UserDTO {
  private String username;
  private String password;
  private String name; 

  public UserModel toUserModel() {
    return new UserModel(
      this.name,
      this.username,
      this.password
    );
  }
}
