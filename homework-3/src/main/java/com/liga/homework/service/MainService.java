package com.liga.homework.service;

import java.io.IOException;
import org.springframework.http.ResponseEntity;

public interface MainService <T> {
  void edit(String type, String id, String field, String newValue);

  void openTestDB();

  T get(String type, String id);

  void delete(String type, String id);

  void add(String type, String classInfo);

  ResponseEntity<String> openHelp() throws IOException;
}
