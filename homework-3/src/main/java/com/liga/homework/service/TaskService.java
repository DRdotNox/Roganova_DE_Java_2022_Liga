package com.liga.homework.service;


import com.liga.homework.model.Task;
import java.util.List;

public interface TaskService {
  List<Task> getTasksFromUser(Long userId);
  Task getOneTaskById(Long id);
  void save(Task task);
  void deleteById(Long id);
  void deleteAll();

  void edit(long id, String field, String newValue);
  void create(String classInfo);

  String createStringParam(String[] base, int start, int end);
}
