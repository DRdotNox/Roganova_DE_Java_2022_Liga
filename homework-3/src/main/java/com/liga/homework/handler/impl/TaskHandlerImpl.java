package com.liga.homework.handler.impl;
import com.liga.homework.handler.TaskHandler;
import com.liga.homework.service.TaskService;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskHandlerImpl implements TaskHandler {
  private final TaskService taskService;

  @Override
  public String edit(String [] params) throws Exception {
    Long id = Long.parseLong(params[0]);
    String field = params[1];
    String newValue = Arrays.stream(params).skip(2).map(Object::toString).collect(Collectors.joining(" "));
    taskService.edit(id,field,newValue);
    return "Успех";
  }

  @Override
  public String get(String [] params) {
    return taskService.getOneTaskById(Long.parseLong(params[0])).toString();
  }

  @Override
  public String delete(String [] params) {
    taskService.deleteById(Long.parseLong(params[0]));
    return "Успех";
  }

  @Override
  public String add(String [] params) {
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
