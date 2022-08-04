package com.liga.homework.controller;

import com.liga.homework.command.CommandFactory;
import com.liga.homework.enums.StatusOfTask;
import com.liga.homework.executor.CommandExecutor;
import com.liga.homework.model.Comment;
import com.liga.homework.model.Task;
import com.liga.homework.service.TaskService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks/v1")
public class TaskController {
  private final TaskService taskService;
//  private final CommandExecutor commandExecutor;
//  private final CommandFactory commandFactory;

  @PostMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public void addTask(@RequestBody (required=false) Task task){
    taskService.save(task);
  }

//  @GetMapping("/all/{id}")
//  @ResponseStatus(HttpStatus.OK)
//  public List<Task> getAllTasksFromUser(@PathVariable("id") Long userId){
//    return taskService.getTasksFromUser(userId);
//  }

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

  @GetMapping("/{id}/comments")
  @ResponseStatus(HttpStatus.OK)
  public List<Comment> getAllComments(@PathVariable("id") Long taskId){
    return taskService.getAllComments(taskId);
  }

  @GetMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  public List<Task> getAllTasks(){
    return taskService.getAllTasks();
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void editTask(@PathVariable("id") Long id, @RequestParam("params") String params) throws Exception {

//    //Вариант 1
//    Command execCommand = commandFactory.create(CommandType.EDIT+ " "+ DataType.TASK
//            + id + " " +params);
//    return commandExecutor.execute(execCommand);

    //Вариант 2
    String[] lines = params.split(" ");
    String field = lines[0];
    String newValue = Arrays.stream(lines).skip(1).map(Object::toString).collect(Collectors.joining(" "));
    taskService.edit(id,field,newValue);

//   Вариант 3
//   Можно вообще передать json и сразу сохранить в репозиторий
  }

}
