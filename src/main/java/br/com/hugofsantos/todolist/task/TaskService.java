package br.com.hugofsantos.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
  @Autowired
  private ITaskRepository taskRepository;

  public TaskModel createTask(TaskModel taskModel) {
    try {
      return this.taskRepository.save(taskModel);
    } catch (Exception e) {
      throw e;
    }
  }
}
