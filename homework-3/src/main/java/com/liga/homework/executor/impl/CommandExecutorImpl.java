package com.liga.homework.executor.impl;

import com.liga.homework.commandMap.CommandMapFactory;
import com.liga.homework.enums.CommandType;
import com.liga.homework.enums.DataType;
import com.liga.homework.executor.CommandExecutor;
import com.liga.homework.command.Command;
import java.util.Map;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Setter
@RequiredArgsConstructor
public class CommandExecutorImpl implements CommandExecutor {
  private final CommandMapFactory commandMapFactory;

  DataType dataType;
  Map<CommandType, Function<String [],String>> commandMap;

  @Override
  public ResponseEntity<String> execute(Command command){
    this.commandMap = commandMapFactory.create(command.getDataType());
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "text/plain; charset=utf-8");
    String result = commandMap.get(command.getCommandType()).apply(command.getParams());
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }

}
