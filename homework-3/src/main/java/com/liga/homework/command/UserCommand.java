package com.liga.homework.command;

import com.liga.homework.enums.CommandType;
import com.liga.homework.enums.DataType;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCommand extends Command {

  int PARAMETER_START = 2;

  public UserCommand(String[] command) {
    this.commandType = CommandType.valueOf(command[0].trim().toUpperCase());
    this.dataType = DataType.USER;
    if(command.length>2) this.params = Arrays.copyOfRange(command,PARAMETER_START, command.length);
    else this.params = new String[]{"no name"};
  }
}
