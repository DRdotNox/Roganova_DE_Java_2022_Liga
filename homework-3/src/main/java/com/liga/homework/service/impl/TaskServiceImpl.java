package com.liga.homework.service.impl;

import com.liga.homework.enums.StatusOfTask;
import com.liga.homework.model.Comment;
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
      case "-h" -> task.setHeader(newValue);
      case "-desc" -> task.setDescription(newValue);
      case "-d" -> {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
          LocalDate date = LocalDate.parse(newValue, formatter);
          task.setDate(date);
      }
      case "-userId" -> task.setUserId(Long.parseLong(newValue));
      case "-s"-> task.setStatus(StatusOfTask.valueOf(newValue));
    }
    taskRepo.save(task);
  }

  @Override
  public void deleteById(Long id) {
    taskRepo.deleteById(id);
  }

  @Override
  public List<Comment> getAllComments(Long taskId) {
    System.out.println("taskId = " + taskId);
    return this.getOneTaskById(taskId).getCommentList();
  }

  @Override
  public void save(Task task) {
    if(task == null){
      task = Task.builder()
              .header("Нет заголовка")
              .description("Нет описания")
              .userId(-1L)
              .date(LocalDate.now())
              .build();
    }
    else{
      if(task.getHeader() == null) task.setHeader("Нет заголовка");
      if(task.getDescription() == null) task.setDescription("Нет описания");
      if(task.getUserId() == null) task.setUserId(-1L);
      if(task.getDate() == null) task.setDate(LocalDate.now());
    }
    System.out.println("task = " + task);
    task.setStatus(StatusOfTask.NEW);
    taskRepo.save(task);
  }

  @Override
  public List<Task> getTasksFromUser(Long userId) {
    return taskRepo.findByUserId(userId);
  }

  @Override
  public List<Task> getAllTasks() {
    return taskRepo.findAll();
  }

  @Override
  public void create(String[] params){

    Task task = new Task();

    String header;
    String desc ;
    Long userId = 0L;
    String stringDate ;

    int headerIndex = -1;
    int descIndex = -1;
    int dateIndex = -1;
    int userIndex = -1;

    for (int i = 0; i < params.length; i++) {
      if(params[i].equals("-h")) headerIndex = i;
      if(params[i].equals("-desc")) descIndex = i;
      if(params[i].equals("-d")) dateIndex = i;
      if(params[i].equals("-userId")) userIndex = i;
    }

    if(headerIndex == -1) header = "Заголовок отсутствует";
    else if (descIndex!=-1) header = createStringParam(params, headerIndex+1, descIndex);
    else if (userIndex!=-1) header = createStringParam(params, headerIndex+1, userIndex);
    else if (dateIndex!=-1) header = createStringParam(params, headerIndex+1, dateIndex);
    else header = createStringParam(params, headerIndex+1, params.length);

    if(descIndex == -1) desc ="Описание отсутствует";
    else if (userIndex!=-1) desc = createStringParam(params,descIndex+1,userIndex);
    else if (dateIndex!=-1) desc = createStringParam(params,descIndex+1,dateIndex);
    else desc = createStringParam(params,descIndex+1, params.length);

    if(userIndex == -1) userId =(long)-1;
    else if (dateIndex!=-1) userId = Long.parseLong(createStringParam(params,userIndex+1, dateIndex));
    else userId = Long.parseLong(createStringParam(params,userIndex+1, params.length));

    if(dateIndex == -1) stringDate = LocalDate.now().format(DateTimeFormatter
        .ofPattern("dd.MM.yyyy"));
    else stringDate =createStringParam(params,dateIndex+1, params.length);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate date = LocalDate.parse(stringDate, formatter);

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
