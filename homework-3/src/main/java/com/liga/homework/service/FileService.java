package com.liga.homework.service;

import java.io.IOException;

import com.opencsv.exceptions.CsvException;
import org.springframework.http.ResponseEntity;

public interface FileService {
  void parseCSVforUser(String filename);
  void parseCSVforTasks(String filename);
  String open();
  String save();
  ResponseEntity<String> getHelpFromFile() throws IOException;
  void saveTaskFile() throws IOException, CsvException;
  void saveUserFile() throws IOException, CsvException;
}
