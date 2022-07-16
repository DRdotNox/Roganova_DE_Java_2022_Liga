package com.liga.homework.command;

import org.springframework.stereotype.Component;

@Component
public class CommandFactoryImpl implements CommandFactory {

  @Override
  public Command create(String command) {
    String [] commandLine = command.split(" ");
    if(commandLine.length == 1) return new FileCommand(commandLine);
    else if (commandLine[1].contains("user")) return new UserCommand(commandLine);
    else return new TaskCommand(commandLine);
  }
}
