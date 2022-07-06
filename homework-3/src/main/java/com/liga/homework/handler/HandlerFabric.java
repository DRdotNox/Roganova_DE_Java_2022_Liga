package com.liga.homework.handler;

import com.liga.homework.enums.DataType;


public interface HandlerFabric {
  Handler create (DataType dataType);
}
