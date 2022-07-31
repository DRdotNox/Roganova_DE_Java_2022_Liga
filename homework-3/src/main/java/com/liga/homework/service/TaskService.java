package com.liga.homework.service;


import com.liga.homework.model.Comment;
import com.liga.homework.model.Task;
import java.util.List;

public interface TaskService {
  List<Task> getTasksFromUser(Long userId);
  Task getOneTaskById(Long id);
  List<Task> getAllTasks();

  void save(Task task);
  void deleteById(Long id);
  void deleteAll();

  void edit(long id, String field, String newValue);
  void create(String [] params);

  String createStringParam(String[] base, int start, int end);

  List<Comment> getAllComments(Long task);
}
