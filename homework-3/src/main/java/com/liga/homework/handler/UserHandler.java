package com.liga.homework.handler;

public interface UserHandler {
  String edit(String [] params);

  String get(String [] params);

  String delete(String [] params);

  String add(String [] params);

  String deleteAll();

  String getALL();
}
