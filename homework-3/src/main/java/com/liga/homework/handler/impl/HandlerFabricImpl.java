package com.liga.homework.handler.impl;

import com.liga.homework.enums.DataType;
import com.liga.homework.handler.Handler;
import com.liga.homework.handler.HandlerFabric;
import com.liga.homework.service.TaskService;
import com.liga.homework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HandlerFabricImpl implements HandlerFabric {

  private final TaskService taskService;
  private final UserService userService;

  @Override
  public Handler create(DataType dataType) {
    if(dataType.equals(DataType.TASK)) return new TaskHandlerImpl(taskService);
    else return new UserHandlerImpl(userService);
  }
}
