package com.liga.homework.executor.impl;

import com.liga.homework.enums.CommandType;
import com.liga.homework.executor.CommandExecutor;
import com.liga.homework.model.Command;
import com.liga.homework.service.FileService;
import com.liga.homework.handler.Handler;
import com.liga.homework.handler.HandlerFabric;
import java.util.HashMap;
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

  private final FileService fileService;

  private final HandlerFabric handlerFabric;

  Handler handler;
  Map<CommandType, Function<String,String>> commandMap;


  @Override
  public ResponseEntity<String> execute(String stringCommand){
    Command command = new Command(stringCommand);
    System.out.println("command.getDataType() = " + command.getDataType());
    this.handler = handlerFabric.create(command.getDataType());
    commandMap = createMap(handler);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "text/plain; charset=utf-8");
    String result = commandMap.get(command.getCommandType()).apply(command.getParams());
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }

  private Map<CommandType, Function<String, String>> createMap(Handler handler){
    Map<CommandType, Function<String,String>> commandMap = new HashMap<>();
      commandMap.put(CommandType.ADD, params ->  handler.add(params));
      commandMap.put(CommandType.GET, params -> handler.get(params));
      commandMap.put(CommandType.GET_ALL, params -> handler.getALL());
      commandMap.put(CommandType.DELETE, params -> handler.delete(params));
      commandMap.put(CommandType.DELETE_ALL, params -> handler.deleteAll());
      commandMap.put(CommandType.EDIT, params -> handler.edit(params));
      commandMap.put(CommandType.TEST, params -> fileService.open());
      commandMap.put(CommandType.SAVE, params -> fileService.save());
      commandMap.put(CommandType.HELP, params -> fileService.getHelpFromFile());
    return commandMap;
  }
}
