package com.liga.homework.service.impl;

import com.liga.homework.StatusOfTask;
import com.liga.homework.model.Task;
import com.liga.homework.model.User;
import com.liga.homework.repo.TaskRepo;
import com.liga.homework.repo.UserRepo;
import com.liga.homework.service.FileService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

  private final UserRepo userRepo;
  private final TaskRepo taskRepo;

  @Override
  public void parseCSVforUser(String filename) {
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        userRepo.save(new User(Long.parseLong(values[0].trim()),values[1].trim(),null));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void parseCSVforTasks(String filename) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.replaceAll("\"","").split(",");
        StatusOfTask status = StatusOfTask.NEW;
        User user = User.builder().id(Long.parseLong(values[3].trim())).build();
        if (values.length == 6)  status = StatusOfTask.valueOf(values [5]);
        Task task = new Task(Long.parseLong(values[0].trim()), values[1].trim(), values[2].trim(),
            user, LocalDate.parse(values[4].trim(), formatter), status);
        taskRepo.save(task);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  @Override
  public Void open(String taskFile, String userFile){
    parseCSVforUser(userFile);
    parseCSVforTasks(taskFile);
    return null;
  }

  @Override
  public ResponseEntity<String> getHelpFromFile() throws IOException {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "text/plain; charset=utf-8");

    String result = Files.readString(Path.of("homework-3/help.txt"), StandardCharsets.UTF_8);

    return new ResponseEntity <>(result, headers, HttpStatus.OK);

  }

  @Override
  public void saveUserFile()
          throws IOException, CsvException {
    CSVWriter writer = new CSVWriter(new FileWriter("homework-3/usersNew"));
    for (User record : userRepo.findAll()) {
      writer.writeNext(record.toString().split(","));
    }
    writer.close();
  }

  @Override
  public void saveTaskFile()
          throws IOException, CsvException {
    CSVWriter writer = new CSVWriter(new FileWriter("homework-3/tasksNew"));
    for (Task record : taskRepo.findAll()) {
      writer.writeNext(record.toString().split(","));
    }
    writer.close();
  }

}
