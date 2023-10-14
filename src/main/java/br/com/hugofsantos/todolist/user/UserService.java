package br.com.hugofsantos.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class UserService {
  @Autowired
  private IUserRepository userRepository;

  public UserModel createUser(UserModel userModel) throws Exception{
    try {
      final var findedUser = this.findUserByUsername(userModel.getUsername());

      if(findedUser != null)
        throw new Exception("Já existe um usuário com esse username", null);

      final var passwordHashed = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

      userModel.setPassword(passwordHashed);
      
      return userRepository.save(userModel);      
    } catch (Exception e) {
      throw e;
    }    
  }

  public UserModel findUserByUsername(String username) {
    try {
      return this.userRepository.findByUsername(username);
    } catch (Exception e) {
      throw e;
    }
  }
}
