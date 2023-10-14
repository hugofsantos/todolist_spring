package br.com.hugofsantos.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  @Autowired
  private TaskService taskService;

  @PostMapping("/")
  public ResponseEntity<TaskModel> create(@RequestBody TaskModel taskModel) {
    try {
      final var task = this.taskService.createTask(taskModel);
    
      return ResponseEntity.status(HttpStatus.CREATED).body(task);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      ResponseEntity.badRequest();
    }
    return null;
  }
}
