package com.liga.homework.service;


import com.liga.homework.SearchCriteria;
import com.liga.homework.model.Comment;
import com.liga.homework.model.Task;
import com.liga.homework.model.User;

import java.util.List;

public interface TaskService {
  List<Task> getFilteredTasks(SearchCriteria searchCriteria);

  Task getOneTaskById(Long id);
  List<Task> getAllTasks();

  void save(Task task);
  void deleteById(Long id);
  void deleteAll();

  void edit(long id, String field, String newValue) throws Exception;
  void create(String [] params);

  String createStringParam(String[] base, int start, int end);

  List<Comment> getAllComments(Long task);
}
