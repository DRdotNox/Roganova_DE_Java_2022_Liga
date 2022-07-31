package com.liga.homework.handler.impl;

import com.liga.homework.handler.FileHandler;
import com.liga.homework.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FileHandlerImpl implements FileHandler {
  private final FileService fileService;

  @Override
  public String openDB() {
    return fileService.open();
  }

  @Override
  public String saveDB() {
    return fileService.save();
  }

  @Override
  public String help() throws IOException {
    return fileService.getHelpFromFile().getBody();
  }
}
