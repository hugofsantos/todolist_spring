package br.com.hugofsantos.todolist.task;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  @Autowired
  private TaskService taskService;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    try {
      final var idUser = request.getAttribute("idUser");
      taskModel.setIdUser((UUID) idUser);

      final var task = this.taskService.createTask(taskModel);
    
      return ResponseEntity.status(HttpStatus.CREATED).body(task);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping("/")
  public ResponseEntity list(HttpServletRequest request) {
    try {
      final var idUser = request.getAttribute("idUser");
      final var tasks = this.taskService.getTasksByUserId((UUID) idUser);

      return ResponseEntity.status(HttpStatus.OK).body(tasks);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {
    try {
      final var idUser = request.getAttribute("idUser");
      final var updatedTask = this.taskService.updateTaskById(id, taskModel, (UUID) idUser);

      return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
