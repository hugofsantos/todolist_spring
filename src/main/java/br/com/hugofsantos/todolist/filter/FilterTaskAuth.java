package br.com.hugofsantos.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.hugofsantos.todolist.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

  @Autowired
  private UserService userService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    final var servletPath = request.getServletPath();

    if(servletPath.startsWith("/tasks")) {
      final var authorization = request.getHeader("Authorization");
      final var base64Auth = authorization.substring("Basic".length()).trim();
      final byte[] authDecoded = Base64.getDecoder().decode(base64Auth);
      final String authString = new String(authDecoded); // <user>:<password> (Exemplo hugosantos:12345)

      final String[] credentials = authString.split(":");
      final String username = credentials[0];
      final String password = credentials[1];


      // Validar usuário
      final var user = this.userService.findUserByUsername(username);

      if(user == null) response.sendError(401, "Usuário não autorizado!");

      else {
        // Validar senha
        final var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

        if(passwordVerify.verified) {
          request.setAttribute("idUser", user.getId());
          filterChain.doFilter(request, response);  
        }else {
          response.sendError(401, "Usuário não autorizado");
        }
      }
    }else doFilter(request, response, filterChain);
  }
}
