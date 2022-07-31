package com.liga.homework.commandMap;

import com.liga.homework.enums.CommandType;
import com.liga.homework.handler.FileHandler;
import com.liga.homework.handler.TaskHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TaskCommandMap implements CommandMap{

  private final TaskHandler handler;
  @Override
  public Map<CommandType, Function<String[], String>> create() {
    Map<CommandType, Function<String [],String>> commandMap = new HashMap<>();
    commandMap.put(CommandType.ADD, params ->  handler.add(params));
    commandMap.put(CommandType.GET, params -> handler.get(params));
    commandMap.put(CommandType.GET_ALL, params -> handler.getALL());
    commandMap.put(CommandType.DELETE, params -> handler.delete(params));
    commandMap.put(CommandType.DELETE_ALL, params -> handler.deleteAll());
    commandMap.put(CommandType.EDIT, params -> {
      try {
        return handler.edit(params);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    return commandMap;
  }

}
