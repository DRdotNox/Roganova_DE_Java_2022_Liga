package com.liga.homework.service;

import java.io.IOException;

import com.opencsv.exceptions.CsvException;
import org.springframework.http.ResponseEntity;

public interface FileService {
  void parseCSVforUser(String filename);
  void parseCSVforTasks(String filename);
  Void open(String taskFile, String userFile);
  ResponseEntity<String> getHelpFromFile() throws IOException;
  void saveTaskFile() throws IOException, CsvException;
  void saveUserFile() throws IOException, CsvException;
}
