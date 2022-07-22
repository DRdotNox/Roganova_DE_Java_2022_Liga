package com.liga.homework.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.liga.homework.StatusOfTask;
import com.liga.homework.model.Task;
import com.liga.homework.repo.TaskRepo;
import com.liga.homework.service.impl.TaskServiceImpl;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

  private TaskService taskService;

  @Mock
  TaskRepo taskRepo;

  @Test
  void Should_GetOneTaskById_When_IdIsValid() {
    Task task = getNewTask();

    when(taskRepo.findById(any())).thenReturn(Optional.ofNullable(task));

    Task result = taskService.getOneTaskById(task.getId());

    assertEquals(task.getHeader(), result.getHeader());
    assertEquals(task.getDescription(), result.getDescription());
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(longs = {-1, -13})
  void Should_TrowsException_When_IdIsInvalid(Long param) {
    assertThrows(EntityNotFoundException.class, ()->taskService.getOneTaskById(param));
  }

  @Test
  void Should_GetAllTasks() {
    List<Task> taskList = getTaskList();

    when(taskRepo.findAll()).thenReturn(taskList);

    List<Task> result = taskService.getAllTasks();

    assertEquals(taskList.size(), result.size());
    assertEquals(taskList.get(0).getHeader(),result.get(0).getHeader());
    assertEquals(taskList.get(1).getDescription(),result.get(1).getDescription());
    assertEquals(taskList.get(2).getStatus(),result.get(2).getStatus());
  }


  @ParameterizedTest
  @NullSource
  @ValueSource(longs = {-1, -13})
  void Should_TrowException_When_IdIsInvalid(Long param) {
    assertThrows(Exception.class, ()->taskService.edit(param, "-h" , "test"));
  }

  @ParameterizedTest
  @ValueSource(strings = {"",
      "-h Пастафарианство для чайников -userId 3",
      "-h Сделать больно 101 -desc Распарсить эту команду правильно -userId 3 -d 11.03.1997"
  })
  void Should_SaveEditedTask(String params) {
    assertDoesNotThrow(()->taskService.create(params));
    verify(taskRepo, times(1)).save(any());
  }

  @BeforeEach
  void setup(){
    taskService = Mockito.spy(new TaskServiceImpl(taskRepo));
  }

  private Task getNewTask() {
    return Task.builder().header("DADA").description("Expecto Patronum spell").build();
  }


  private List<Task> getTaskList() {
    Task task1 = Task.builder().header("Transfiguration").build();
    Task task2 = Task.builder().description("Turn rat into glass").build();
    Task task3 = Task.builder().status(StatusOfTask.DONE).build();
    return List.of(task1,task2,task3);
  }

}