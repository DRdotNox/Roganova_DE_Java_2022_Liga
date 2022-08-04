package com.liga.homework.service;

import com.liga.homework.model.User;
import java.util.List;

public interface UserService {
  List<User> getAllUsers();
  User getOneUserById(Long id);
  void edit(Long id, String newValue);
  void save(User user);
  void deleteById(Long id);
  void deleteAll();

  User getUserWithMaxTasks();
}
