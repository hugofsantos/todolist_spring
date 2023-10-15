package br.com.hugofsantos.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hugofsantos.todolist.utils.Utils;

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

  public List<TaskModel> getTasksByUserId(UUID userId) {
    try {
      return this.taskRepository.findByIdUser(userId);
    } catch (Exception e) {
      throw e;
    }
  }

  public TaskModel getTaskById(UUID id) {
    try {
      final Optional<TaskModel> task = this.taskRepository.findById(id);
      
      return task.isPresent() ? task.get() : null;
    } catch (Exception e) {
      throw e;
    }
  }

  public TaskModel updateTaskById(UUID idTask, TaskModel task, UUID idUser) throws Exception {
    try {
      final var findedTask = this.getTaskById(idTask);

      if(findedTask != null) {
        if(!findedTask.getIdUser().equals(idUser)) throw new Exception("Usuário não tem permissão para alterar essa tarefa");


        Utils.copyNonNullProperties(task, findedTask); // Copia todos os valores não nulos de task para findedTask

        return this.taskRepository.save(findedTask);
      }

      return null;
    } catch (Exception e) {
      throw e;
    }
  }
}
