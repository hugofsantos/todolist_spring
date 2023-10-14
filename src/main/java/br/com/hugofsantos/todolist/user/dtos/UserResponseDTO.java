package br.com.hugofsantos.todolist.user.dtos;

import java.util.UUID;

import br.com.hugofsantos.todolist.user.UserModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE) // Cria construtor privado com todos os campos
@Getter
public class UserResponseDTO {
  private UUID id;
  private String name;
  private String username;

  public static UserResponseDTO getUserResponseDTOByUserModel(UserModel user) {
    return new UserResponseDTO(user.getId(), user.getName(), user.getUsername());
  }
}
