package com.liga.homework.service.impl;

import com.liga.homework.StatusOfTask;
import com.liga.homework.model.Task;
import com.liga.homework.repo.TaskRepo;
import com.liga.homework.service.TaskService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final TaskRepo taskRepo;

  @Override
  public Task getOneTaskById(Long id) {
    return taskRepo.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public void deleteAll() {
    taskRepo.deleteAll();
  }

  @Override
  public void edit(long id, String field, String newValue) {

    Task task = taskRepo.findById(id).orElseThrow(EntityNotFoundException::new);

    switch (field) {
      case "header" -> task.setHeader(newValue);
      case "description" -> task.setDescription(newValue);
      case "date" -> {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
          LocalDate date = LocalDate.parse(newValue, formatter);
          task.setDate(date);
      }
      case "status"-> task.setStatus(StatusOfTask.valueOf(newValue));
    }
    taskRepo.save(task);
  }

  @Override
  public void deleteById(Long id) {
    taskRepo.deleteById(id);
  }

  @Override
  public void save(Task task) {
    taskRepo.save(task);
  }

  @Override
  public List<Task> getTasksFromUser(Long userId) {
    return taskRepo.findByUserId(userId);
  }

  @Override
  public void create(String classInfo){
    String[] params = classInfo.split(" ");

    int headerIndex = 0;
    int descIndex = 0;
    int dateIndex = 0;
    int userIndex = 0;

    for (int i = 0; i < params.length; i++) {
      if(params[i].equals("-h")) headerIndex = i;
      if(params[i].equals("-desc")) descIndex = i;
      if(params[i].equals("-d")) dateIndex = i;
      if(params[i].equals("-userId")) userIndex = i;
    }

    String header = createStringParam(params, headerIndex+1, descIndex);
    String desc = createStringParam(params,descIndex+1,userIndex);
    Long userId = Long.parseLong(createStringParam(params,userIndex+1, dateIndex));
    String stringDate =createStringParam(params,dateIndex+1, params.length);


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate date = LocalDate.parse(stringDate, formatter);

    Task task = new Task();
    task.setHeader(header);
    task.setDescription(desc);
    task.setUserId(userId);
    task.setDate(date);
    task.setStatus(StatusOfTask.NEW);

    taskRepo.save(task);
  }

  @Override
  public String createStringParam(String[] base, int start, int end){

    String result ="";
    for (int i = start; i < end; i++) {
      result = result.concat(" ").concat(base[i]);
    }
    return result.trim();
  }

}
