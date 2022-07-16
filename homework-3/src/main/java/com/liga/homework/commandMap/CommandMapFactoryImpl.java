package com.liga.homework.commandMap;

import com.liga.homework.enums.CommandType;
import com.liga.homework.enums.DataType;
import com.liga.homework.handler.FileHandler;
import com.liga.homework.handler.TaskHandler;
import com.liga.homework.handler.UserHandler;
import java.util.Map;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandMapFactoryImpl implements CommandMapFactory {

  private final UserHandler userHandler;
  private final TaskHandler taskHandler;
  private final FileHandler fileHandler;

  @Override
  public Map<CommandType, Function<String[], String>> create(DataType dataType) {
    if (dataType.equals(DataType.USER))
      return new UserCommandMap(userHandler).create();
    else if (dataType.equals(DataType.TASK))
      return new TaskCommandMap(taskHandler).create();
    else
      return new FileCommandMap(fileHandler).create();
  }
}
