package com.liga.homework.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTaskDTO {
  private String name;
  private List<Task> taskList;

  @Override
  public String toString() {
    return name + "\n" + taskList.toString();
  }
}
