package com.liga.homework.commandMap;

import com.liga.homework.enums.CommandType;
import com.liga.homework.handler.FileHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FileCommandMap implements CommandMap {
  private final FileHandler handler;
  @Override
  public Map<CommandType, Function<String[], String>> create() {
    Map<CommandType, Function<String [],String>> commandMap = new HashMap<>();
    commandMap.put(CommandType.HELP, params -> {
      try {
        return handler.help();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
    commandMap.put(CommandType.TEST, params -> handler.openDB());
    commandMap.put(CommandType.SAVE, params -> handler.saveDB());
    return commandMap;
  }
}
