package com.liga.homework.model;

import com.liga.homework.enums.CommandType;
import com.liga.homework.enums.DataType;
import java.util.Arrays;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Command {

  private final int ACCEPTABLE_LENGTH = 3;

  CommandType commandType;
  DataType dataType;
  String params;

  public Command(String command) {

    String[] lines = command.split(" ");
    if (lines.length>=ACCEPTABLE_LENGTH ){
      for (int i = ACCEPTABLE_LENGTH; i < lines.length; i++) {
        lines[ACCEPTABLE_LENGTH-1] = lines[ACCEPTABLE_LENGTH-1].concat(" ").concat(lines[i]);
      }
      this.dataType = DataType.valueOf(lines[1].toUpperCase());
      this.params = lines[2];
    }
    else
    {
      if (lines.length == 1) {
        this.dataType = DataType.USER;
      }
      else {
        this.dataType = DataType.valueOf(lines[1].toUpperCase());
      }
      this.params = "N/I";
    }

    if(this.params.toLowerCase().equals("all")){
      if(CommandType.valueOf(lines[0].toUpperCase()).equals(CommandType.DELETE))  this.commandType = CommandType.DELETE_ALL;
      else this.commandType = CommandType.GET_ALL;
    }
    else this.commandType = CommandType.valueOf(lines[0].toUpperCase());
  }
}
