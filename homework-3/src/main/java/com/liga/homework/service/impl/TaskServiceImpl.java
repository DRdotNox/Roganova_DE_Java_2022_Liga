package com.liga.homework.service.impl;

import com.liga.homework.SearchCriteria;
import com.liga.homework.enums.StatusOfTask;
import com.liga.homework.model.Task;
import com.liga.homework.model.User;
import com.liga.homework.repo.TaskRepo;
import com.liga.homework.repo.UserRepo;
import com.liga.homework.service.TaskService;
import com.liga.homework.specification.TaskSpecification;
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
  private final UserRepo userRepo;

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
      case "-userId" -> {
        Long userId = Long.parseLong(newValue);
        User user =  userRepo.findById(userId).orElseThrow(EntityNotFoundException::new);
        task.setUser(user);
      }
      case "-s"-> task.setStatus(StatusOfTask.valueOf(newValue));
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
  public List<Task> getFilteredTasks(SearchCriteria searchCriteria) {
    TaskSpecification spec = new TaskSpecification(searchCriteria);
    return taskRepo.findAll(spec);
  }

  @Override
  public List<Task> getAllTasks() {
    return taskRepo.findAll();
  }

  @Override
  public void create(String[] params){

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

    Task task = new Task();
    task.setHeader(header);
    task.setDescription(desc);
    if(userId!=null)task.setUser(User.builder().id(userId).build());
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
