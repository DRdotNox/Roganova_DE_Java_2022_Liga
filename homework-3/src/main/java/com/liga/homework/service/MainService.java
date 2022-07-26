package com.liga.homework.service;

import com.liga.homework.model.UserTaskDTO;
import java.io.IOException;

import com.opencsv.exceptions.CsvException;
import org.springframework.http.ResponseEntity;

public interface MainService <T> {
  void edit(String type, String id, String field, String newValue);

  void openTestDB();

  T get(String type, String id);

  void delete(String type, String id);

  void add(String type, String classInfo);

  ResponseEntity<String> openHelp() throws IOException;

  void saveFiles() throws IOException, CsvException;

  ResponseEntity<UserTaskDTO> getUserWithMaxTasks(String spec, String dateFrom, String dateTo);
}
