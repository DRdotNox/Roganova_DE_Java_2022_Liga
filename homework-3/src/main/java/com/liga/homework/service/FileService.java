package com.liga.homework.service;

import java.io.IOException;

import com.opencsv.exceptions.CsvException;

public interface FileService {
  void parseCSVforUser(String filename);
  void parseCSVforTasks(String filename);
  String open();
  String save();
  String getHelpFromFile();
  void saveTaskFile() throws IOException, CsvException;
  void saveUserFile() throws IOException, CsvException;
}
