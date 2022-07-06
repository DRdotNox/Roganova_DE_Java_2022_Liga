package com.liga.homework.handler.impl;

import com.liga.homework.handler.Handler;
import com.liga.homework.service.TaskService;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TaskHandlerImpl implements Handler {
  private final TaskService taskService;

  @Override
  public String edit(String params) {
    String[] lines = params.split(" ");
    Long id = Long.parseLong(lines[0]);
    String field = lines[1];
    String newValue = Arrays.stream(lines).skip(2).map(Object::toString).collect(Collectors.joining(" "));
    taskService.edit(id,field,newValue);
    return "Успех";
  }

  @Override
  public String get(String params) {
    return taskService.getOneTaskById(Long.parseLong(params)).toString();
  }

  @Override
  public String delete(String params) {
    taskService.deleteById(Long.parseLong(params));
    return "Успех";
  }

  @Override
  public String add(String params) {
    taskService.create(params);
    return "Успех";
  }

  @Override
  public String deleteAll() {
    taskService.deleteAll();
    return "Успех";
  }

  @Override
  public String getALL() {
    return taskService.getAllTasks().toString();
  }
}
