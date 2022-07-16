package com.liga.homework.command;

import com.liga.homework.enums.CommandType;
import com.liga.homework.enums.DataType;
import java.util.Arrays;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskCommand extends Command {

  int PARAMETER_START = 2;

  public TaskCommand(String[] command) {
    this.commandType = CommandType.valueOf(command[0].trim().toUpperCase());
    this.dataType = DataType.TASK;
    if(command.length>2) this.params = Arrays.copyOfRange(command,PARAMETER_START, command.length);
    else this.params = new String[]{""};
  }
}
