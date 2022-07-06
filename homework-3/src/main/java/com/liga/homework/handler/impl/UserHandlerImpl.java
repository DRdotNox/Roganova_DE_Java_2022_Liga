package com.liga.homework.handler.impl;

import com.liga.homework.handler.Handler;
import com.liga.homework.model.User;
import com.liga.homework.service.UserService;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserHandlerImpl implements Handler {

  private final UserService userService;

  @Override
  public String edit(String params) {
    String[] lines = params.split(" ");
    Long id = Long.parseLong(lines[0]);
    String newValue="";
    newValue = Arrays.stream(lines).skip(2).map(Object::toString).collect(Collectors.joining(" "));
    userService.edit(id, newValue.trim());
    return "Успех";
  }

  @Override
  public String getALL() {
    return userService.getAllUsers().toString();
  }

  @Override
  public String get(String param) {
    return userService.getOneUserById(Long.parseLong(param)).toString();

  }

  @Override
  public String delete(String param) {
    userService.deleteById(Long.parseLong(param));
    return "Успех";
  }

  @Override
  public String deleteAll() {
    userService.deleteAll();
    return "Успех";
  }

  @Override
  public String add(String params) {
    userService.save(User.builder().name(params).build());
    return "Успех";
  }
}
