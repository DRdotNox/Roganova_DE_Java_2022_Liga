package com.liga.homework.handler;

import com.liga.homework.service.impl.FileServiceImpl;

public interface Handler<T> {

  String edit(String params);

  String get(String params);

  String delete(String params);

  String add(String params);

  String deleteAll();

  String getALL();

}
