package com.liga.homework.controller;

import com.liga.homework.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/command")
public class MainController {

  private final MainService mainService;

  @GetMapping("/{command}")
  public <T> T commandLine(@PathVariable("command") String command) {
    String[] lines = command.split(" ");
    if (lines.length > 5 && lines[0].equals("edit")) {
      for (int i = 5; i < lines.length; i++) {
        lines[4] = lines[4].concat(" ").concat(lines[i]);
      }
    } else if (lines[0].equals("add")) {
      for (int i = 3; i < lines.length; i++) {
        lines[2] = lines[2].concat(" ").concat(lines[i]);
      }
    }

    switch (lines[0]) {
      case "open" -> mainService.open();
      case "edit" -> mainService.edit(lines[1], lines[2], lines[3], lines[4]);
      case "get" -> {
        if (lines.length < 3) {
          return (T) mainService.get(lines[1], " ");
        } else {
          return (T) mainService.get(lines[1], lines[2]);
        }
      }
      case "delete" -> mainService.delete(lines[1], lines[2]);
      case "add" -> {
        if (lines.length < 3) {
          mainService.add(lines[1], " ");
        } else {
          mainService.add(lines[1], lines[2]);
        }
      }
    }

    return null;
  }
}
