package com.liga.homework.command;

import com.liga.homework.command.Command;

public interface CommandFactory {

  Command create(String command);

}
