package com.liga.homework.commandMap;

import com.liga.homework.enums.CommandType;
import com.liga.homework.enums.DataType;
import java.util.Map;
import java.util.function.Function;

public interface CommandMapFactory {
  Map<CommandType, Function<String [], String>> create(DataType dataType);
}
