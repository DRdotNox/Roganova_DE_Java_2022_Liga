package com.liga.homework.service.impl;

import com.liga.homework.SearchCriteria;
import com.liga.homework.model.Task;
import com.liga.homework.model.User;
import com.liga.homework.model.UserTaskDTO;
import com.liga.homework.service.FileService;
import com.liga.homework.service.MainService;
import com.liga.homework.service.TaskService;
import com.liga.homework.service.UserService;
import java.io.IOException;

import com.opencsv.exceptions.CsvException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainServiceImpl <T> implements MainService {
  private final UserService userService;
  private final TaskService taskService;
  private final FileService fileService;

  @Override
  public void saveFiles() throws IOException, CsvException {
    fileService.saveTaskFile();
    fileService.saveUserFile();
  }

  @Override
  public void add(String type, String classInfo) {
    if(classInfo.isEmpty()) classInfo = "Нет имени";
    if(type.equals("user")) userService.save(User.builder().name(classInfo).build());
    else taskService.create(classInfo.split(" "));
  }

  @Override
  public void openTestDB() {
    fileService.parseCSVforTasks("homework-3/tasks.csv");
    fileService.parseCSVforUser("homework-3/users.csv");
  }

  @Override
  public ResponseEntity<String> getUserWithMaxTasks(String status, String dateFrom, String dateTo) {

    User user = userService.getUserWithMaxTasks();
    SearchCriteria searchCriteria = new SearchCriteria(user,status,dateFrom,dateTo);
    List<Task> taskList= taskService.getFilteredTasks(searchCriteria);
    return new ResponseEntity<>(new UserTaskDTO(user.getName(),taskList).toString(), HttpStatus.OK);
  }

  @Override
  public T get(String type, String id) {
    if(type.equals("user")) {
      return (T) userService.getOneUserById(Long.parseLong(id));
    }
    if(type.equals("task")){
      return (T) taskService.getOneTaskById(Long.parseLong(id));
    }

    if(type.equals("users")) {
      return (T) userService.getAllUsers();
    }
    if(type.equals("tasks")) {
      return (T) taskService.getAllTasks();
    }

    return null;
  }

  @Override
  public void delete(String type, String id) {
    if(type.contains("user")) {
      if(id.equals("all")) userService.deleteAll();
      else userService.deleteById(Long.parseLong(id));
    }
    else if (type.contains("task")) {
      if(id.equals("all")) taskService.deleteAll();
      else taskService.deleteById(Long.parseLong(id));
    }

  }

  @Override
  public void edit(String type, String id, String field, String newValue) throws Exception {
    if(type.equals("user")) userService.edit(Long.parseLong(id),newValue);
    else taskService.edit(Long.parseLong(id),field,newValue);
  }

  @Override
  public ResponseEntity<String> openHelp() throws IOException {
    return fileService.getHelpFromFile();
  }
}
