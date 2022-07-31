package com.liga.homework.handler;

import java.io.IOException;

public interface FileHandler{

  String openDB();
  String saveDB();
  String help() throws IOException;
}
