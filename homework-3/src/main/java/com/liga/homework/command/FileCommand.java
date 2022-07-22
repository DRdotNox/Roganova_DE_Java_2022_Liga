package com.liga.homework.command;

import com.liga.homework.enums.CommandType;
import com.liga.homework.enums.DataType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileCommand extends Command {

  public FileCommand(String[] command) {
    this.dataType = DataType.FILE;
    this.params = null;
    this.commandType = CommandType.valueOf(command[0].trim().toUpperCase());
  }
}
