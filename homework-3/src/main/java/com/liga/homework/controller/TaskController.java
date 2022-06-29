package com.liga.homework.controller;

import com.liga.homework.model.Task;
import com.liga.homework.service.TaskService;
import java.util.List;
import javax.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
  private final TaskService taskService;

  @PostMapping("/save")
  @ResponseStatus(HttpStatus.OK)
  public void saveTask(@RequestBody Task task){
    taskService.save(task);
  }

  @GetMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  public List<Task> getAllTasksFromUser(@PathParam("userId") Long userId){
    return taskService.getTasksFromUser(userId);
  }
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteById(@PathVariable("id") Long id){
    taskService.deleteById(id);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Task getOneTask(@PathVariable("id") Long id){
    return taskService.getOneTaskById(id);
  }

  @DeleteMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  public void deleteAllTasks(){
    taskService.deleteAll();
  }

}
