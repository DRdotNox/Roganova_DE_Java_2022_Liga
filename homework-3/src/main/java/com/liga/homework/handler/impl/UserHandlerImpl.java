package com.liga.homework.handler.impl;

import com.liga.homework.enums.UserRole;
import com.liga.homework.handler.UserHandler;
import com.liga.homework.model.User;
import com.liga.homework.service.UserService;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHandlerImpl implements UserHandler {

  private final UserService userService;

  @Override
  public String edit(String[] params) {
    Long id = Long.parseLong(params[0]);
    String newValue="";
    newValue = Arrays.stream(params).skip(2).map(Object::toString).collect(Collectors.joining(" "));
    userService.edit(id, newValue.trim());
    return "Успех";
  }

  @Override
  public String getALL() {
    return userService.getAllUsers().toString();
  }

  @Override
  public String get(String[] param) {
    return userService.getOneUserById(Long.parseLong(param[0])).toString();

  }

  @Override
  public String delete(String[] param) {
    userService.deleteById(Long.parseLong(param[0]));
    return "Успех";
  }

  @Override
  public String deleteAll() {
    userService.deleteAll();
    return "Успех";
  }

  @Override
  public String add(String [] params) {
    String name = Arrays.stream(params).collect(Collectors.joining(" "));
    userService.save(User.builder().name(name).role(UserRole.ROLE_USER).build());
    return "Успех";
  }
}
