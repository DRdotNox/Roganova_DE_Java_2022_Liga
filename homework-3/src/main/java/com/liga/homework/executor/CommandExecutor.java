package com.liga.homework.executor;

import com.liga.homework.command.Command;
import org.springframework.http.ResponseEntity;

public interface CommandExecutor {
  ResponseEntity<String> execute(Command command);
}
