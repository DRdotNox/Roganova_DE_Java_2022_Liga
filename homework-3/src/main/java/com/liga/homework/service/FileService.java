package com.liga.homework.service;

public interface FileService {
  void parseCSVforUser(String filename);
  void parseCSVforTasks(String filename);
  Void open(String taskFile, String userFile);
}
