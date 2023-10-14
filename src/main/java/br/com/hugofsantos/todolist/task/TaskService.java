package br.com.hugofsantos.todolist.task;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
  @Autowired
  private ITaskRepository taskRepository;

  public TaskModel createTask(TaskModel taskModel) throws Exception {
    try {
      if(LocalDateTime.now().isAfter(taskModel.getStartAt())) throw new Exception("A data de início deve ser maior do que a data atual!");
      if(taskModel.getStartAt().isAfter(taskModel.getEndAt())) throw new Exception("A data de início deve ser menor do que a data de finalização da tarefa");

      return this.taskRepository.save(taskModel);
    } catch (Exception e) {
      throw e;
    }
  }
}
