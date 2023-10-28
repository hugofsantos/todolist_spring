package br.com.hugofsantos.todolist.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskServiceTest {
  @Mock
  private ITaskRepository taskRepository;

  @Autowired
  @InjectMocks
  private TaskService taskService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Should create a task")
  void createTaskCase1() throws Exception {
    // Arrange
    final TaskModel taskMock = new TaskModel(
      UUID.randomUUID(),
      UUID.randomUUID(),
      "Descrição da tarefa",
      "Tarefa Teste",
      LocalDateTime.of(2024, 5, 6, 0, 0, 0, 0),
      LocalDateTime.of(2024, 12, 1, 0, 0, 0, 0),
      "ALTA",
      LocalDateTime.now()
    );

    when(taskRepository.save(taskMock)).thenReturn(taskMock);
    
    // Act
    final TaskModel createdTask = this.taskService.createTask(taskMock);

    // Assert
    assertEquals(taskMock, createdTask);
    verify(taskRepository, times(1)).save(taskMock); // Se o método save repository foi chamado pelo menos uma vez
  }

  @Test
  @DisplayName("Should throw an Exception when task startDate is less then the current datetime") 
  void createTaskCase2() throws Exception{
    // Arrange
    final TaskModel taskMock = new TaskModel(
      UUID.randomUUID(),
      UUID.randomUUID(),
      "Descrição da tarefa",
      "Tarefa Teste",
      LocalDateTime.of(2020, 5, 6, 0, 0, 0, 0),
      LocalDateTime.of(2024, 12, 1, 0, 0, 0, 0),
      "ALTA",
      LocalDateTime.now()
    );     

    Exception thrown = Assertions.assertThrows(Exception.class, () -> {
      // Act
      this.taskService.createTask(taskMock);
    });

    Assertions.assertEquals("A data de início deve ser maior do que a data atual!", thrown.getMessage());
    verify(taskRepository, times(0)).save(taskMock);
  }

  @Test
  @DisplayName("Should throw an Exception when task startDate is less then the current datetime") 
  void createTaskCase3() throws Exception{
    // Arrange
    final TaskModel taskMock = new TaskModel(
      UUID.randomUUID(),
      UUID.randomUUID(),
      "Descrição da tarefa",
      "Tarefa Teste",
      LocalDateTime.of(2025, 5, 6, 0, 0, 0, 0),
      LocalDateTime.of(2024, 12, 1, 0, 0, 0, 0),
      "ALTA",
      LocalDateTime.now()
    );     

    Exception thrown = Assertions.assertThrows(Exception.class, () -> {
      // Act
      this.taskService.createTask(taskMock);
    });

    Assertions.assertEquals("A data de início deve ser menor do que a data de finalização da tarefa", thrown.getMessage());
    verify(taskRepository, times(0)).save(taskMock);
  }  
  
}
