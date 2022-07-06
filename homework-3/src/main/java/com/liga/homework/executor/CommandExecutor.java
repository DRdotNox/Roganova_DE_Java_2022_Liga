package com.liga.homework.executor;

import org.springframework.http.ResponseEntity;

public interface CommandExecutor {
  ResponseEntity<String> execute(String command);
}
