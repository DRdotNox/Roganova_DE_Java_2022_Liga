package com.liga.homework.service;

public interface MainService <T> {
  void edit(String type, String id, String field, String newValue);

  void open();

  T get(String type, String id);

  void delete(String type, String id);

  void add(String type, String classInfo);
}
