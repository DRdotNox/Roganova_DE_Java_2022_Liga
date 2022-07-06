package com.liga.homework.controller;

import com.liga.homework.executor.CommandExecutor;
import java.io.IOException;

import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {
  private final CommandExecutor commandExecutor;

  @GetMapping("/{command}")
  public ResponseEntity<String> commandLine(@PathVariable("command") String command) throws IOException, CsvException {
    return commandExecutor.execute(command);
  }
}
