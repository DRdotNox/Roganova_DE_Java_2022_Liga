package com.liga.homework.controller;

import com.liga.homework.service.MainService;
import java.io.IOException;

import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

  private final MainService mainService;

  private final int COMMAND_TYPE = 0;
  private final int DATA_TYPE = 1;
  private final int PARAM_ID = 2;

  private final int ADD_PARAMS_START_INDEX = 2;
  private final int ADD_PARAMS_MIN_NUMBER = 3;

  private final int GET_PARAMS_MAX_NUMBER = 3;

  private final int EDIT_PARAM_FIELD = 3;
  private final int EDIT_TASK_PARAMS_START_INDEX = 4;


  @GetMapping("/{command}")
  public <T> T commandLine(@PathVariable("command") String command) throws IOException, CsvException {
    String[] lines = command.split(" ");
    if (lines.length > EDIT_TASK_PARAMS_START_INDEX+1 && lines[COMMAND_TYPE].equals("edit")) {
      for (int i = EDIT_TASK_PARAMS_START_INDEX+1; i < lines.length; i++) {
        lines[EDIT_TASK_PARAMS_START_INDEX] = lines[EDIT_TASK_PARAMS_START_INDEX].concat(" ").concat(lines[i]);
      }
    } else if (lines[COMMAND_TYPE].equals("add")) {
      for (int i = ADD_PARAMS_MIN_NUMBER; i < lines.length; i++) {
        lines[ADD_PARAMS_START_INDEX] = lines[ADD_PARAMS_START_INDEX].concat(" ").concat(lines[i]);
      }
    }

    switch (lines[COMMAND_TYPE]) {
      case "test" -> mainService.openTestDB();
      case "edit" -> mainService.edit(lines[DATA_TYPE], lines[PARAM_ID], lines[EDIT_PARAM_FIELD], lines[4]);
      case "get" -> {
        if (lines.length < GET_PARAMS_MAX_NUMBER) {
          return (T) mainService.get(lines[DATA_TYPE], " ");
        } else {
          return (T) mainService.get(lines[DATA_TYPE], lines[PARAM_ID]);
        }
      }
      case "delete" -> mainService.delete(lines[DATA_TYPE], lines[PARAM_ID]);
      case "add" -> {
        if (lines.length < ADD_PARAMS_MIN_NUMBER) {
          mainService.add(lines[DATA_TYPE], "");
        } else {
          mainService.add(lines[DATA_TYPE], lines[ADD_PARAMS_START_INDEX]);
        }
      }
      case "help" -> {
        return (T) mainService.openHelp();
      }

      case "save" -> mainService.saveFiles();

    }

    return null;
  }
}
