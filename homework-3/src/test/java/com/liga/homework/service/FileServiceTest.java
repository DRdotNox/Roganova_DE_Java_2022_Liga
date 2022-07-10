package com.liga.homework.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.liga.homework.StatusOfTask;
import com.liga.homework.model.Task;
import com.liga.homework.model.User;
import com.liga.homework.repo.TaskRepo;
import com.liga.homework.repo.UserRepo;
import com.liga.homework.service.impl.FileServiceImpl;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
class FileServiceTest {

  private FileService fileService;

  @Mock
  UserRepo userRepo;
  @Mock
  TaskRepo taskRepo;

  @Test
  void Should_SaveAllUsersInDB() {
    assertDoesNotThrow(()->fileService.parseCSVforUser("users.csv"));
    verify(userRepo, times(5)).save(any());
  }

  @Test
  void Should_TrowException_When_UserLineIsInvalid() {
    assertThrows(Exception.class,()->fileService.parseCSVforUser("src/test/resources/usersInvalidId.csv"));
  }

  @Test
  void Should_SaveAllTasksInDB() {
    assertDoesNotThrow(()->fileService.parseCSVforTasks("tasks.csv"));
    verify(taskRepo, times(8)).save(any());
  }

  @ParameterizedTest
  @ValueSource(strings = {"src/test/resources/tasksInvalidDate.csv", "src/test/resources/tasksInvalidId.csv"})
  void Should_TrowException_When_TaskLineIsInvalid(String param) {
    assertThrows(Exception.class,()->fileService.parseCSVforTasks(param));
  }

  @Test
  void getHelpFromFile() throws IOException {
    String result = Files.readString(Path.of("help.txt"), StandardCharsets.UTF_8);
    assertEquals(result, fileService.getHelpFromFile().getBody());
  }

  @Test
  void Should_SaveUserFile() {

    when(userRepo.findAll()).thenReturn(getUserList());

    assertDoesNotThrow(()->fileService.saveUserFile());
    assertEquals(true, new File("usersNew.csv").isFile());
  }

  @Test
  void Should_SaveTaskFile() {

    when(taskRepo.findAll()).thenReturn(getTaskList());

    assertDoesNotThrow(()->fileService.saveTaskFile());
    assertEquals(true, new File("tasksNew.csv").isFile());
  }

  @BeforeEach
  void setup(){
    fileService = Mockito.spy(new FileServiceImpl(userRepo,taskRepo));
  }

  private List<User> getUserList() {
    User user1 = User.builder().name("Tom Marvolo Riddle").build();
    User user2 = User.builder().name("Harry James Potter").build();
    User user3 = User.builder().name("Dobby").build();
    return List.of(user1,user2,user3);
  }


  private List<Task> getTaskList() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate date = LocalDate.parse("11.03.1997",formatter);
    Task task1 = Task.builder().header("Transfiguration").date(date).build();
    Task task2 = Task.builder().description("Turn rat into glass").date(date).build();
    Task task3 = Task.builder().date(date).status(StatusOfTask.DONE).build();
    return List.of(task1,task2,task3);
  }

}