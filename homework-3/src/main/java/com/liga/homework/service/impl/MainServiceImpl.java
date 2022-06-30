package com.liga.homework.service.impl;

import com.liga.homework.model.User;
import com.liga.homework.service.FileService;
import com.liga.homework.service.MainService;
import com.liga.homework.service.TaskService;
import com.liga.homework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainServiceImpl <T> implements MainService {
  private final UserService userService;
  private final TaskService taskService;
  private final FileService fileService;

  @Override
  public void add(String type, String classInfo) {
    if(type.equals("user")) userService.save(User.builder().name(classInfo).build());
    else taskService.create(classInfo);
  }

  @Override
  public void open() {
    fileService.parseCSVforTasks("homework-3/tasks.csv");
    fileService.parseCSVforUser("homework-3/users.csv");
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
    if(type.equals("user")) userService.deleteById(Long.parseLong(id));
    else taskService.deleteById(Long.parseLong(id));
  }

  @Override
  public void edit(String type, String id, String field, String newValue) {
    if(type.equals("user")) userService.edit(Long.parseLong(id),newValue);
    else taskService.edit(Long.parseLong(id),field,newValue);
  }
}
