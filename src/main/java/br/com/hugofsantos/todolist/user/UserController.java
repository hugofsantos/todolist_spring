package br.com.hugofsantos.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hugofsantos.todolist.user.dtos.UserDTO;
import br.com.hugofsantos.todolist.user.dtos.UserResponseDTO;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody UserDTO userDTO) {
    try{
      final var user = this.userService.createUser(userDTO.toUserModel());
      
      return ResponseEntity.status(HttpStatus.CREATED).body(UserResponseDTO.getUserResponseDTOByUserModel(user));
    }catch(Exception e) {
      System.err.println(e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
