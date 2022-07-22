package com.liga.homework.commandMap;
import com.liga.homework.enums.CommandType;
import java.util.Map;
import java.util.function.Function;


public interface CommandMap {
 Map<CommandType, Function<String[], String>> create();
}
