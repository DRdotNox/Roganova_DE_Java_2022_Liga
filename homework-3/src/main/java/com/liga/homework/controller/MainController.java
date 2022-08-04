package com.liga.homework.controller;

import com.liga.homework.command.CommandFactory;
import com.liga.homework.executor.CommandExecutor;
import com.liga.homework.command.Command;
import java.io.IOException;

import com.liga.homework.model.UserTaskDTO;
import com.liga.homework.service.MainService;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
public class MainController {
  private final CommandExecutor commandExecutor;
  private final CommandFactory commandFactory;
  private final MainService mainService;

  @GetMapping("/cli")
  public ResponseEntity<String> commandLine(@RequestParam("command") String command) throws IOException, CsvException {
    System.out.println("command = " + command);
    Command execCommand = commandFactory.create(command);
    return commandExecutor.execute(execCommand);
  }

  @GetMapping("/max")
  ResponseEntity<String> getUserWithMaxTasks(@PathParam("status") String status,
                                                  @PathParam(value = "dateFrom") String dateFrom,
                                                  @PathParam(value = "dateTo") String dateTo) {

    return mainService.getUserWithMaxTasks(status, dateFrom, dateTo);
  }
}
