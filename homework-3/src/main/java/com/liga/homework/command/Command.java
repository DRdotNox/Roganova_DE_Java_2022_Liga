package com.liga.homework.command;

import com.liga.homework.enums.CommandType;
import com.liga.homework.enums.DataType;
import lombok.Getter;

@Getter
public abstract class Command {
  CommandType commandType;
  DataType dataType;
  String [] params;
}
