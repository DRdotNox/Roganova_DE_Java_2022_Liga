package com.liga.homework.controller;

import com.liga.homework.command.CommandFactory;
import com.liga.homework.executor.CommandExecutor;
import com.liga.homework.command.Command;
import java.io.IOException;

import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {
  private final CommandExecutor commandExecutor;
  private final CommandFactory commandFactory;

  @GetMapping("/cli")
  public ResponseEntity<String> commandLine(@RequestParam("command") String command) throws IOException, CsvException {
    System.out.println("command = " + command);
    Command execCommand = commandFactory.create(command);
    return commandExecutor.execute(execCommand);
  }
}
