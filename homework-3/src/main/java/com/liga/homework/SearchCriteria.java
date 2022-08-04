package com.liga.homework;

import com.liga.homework.enums.StatusOfTask;
import com.liga.homework.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {

  private User user;
  private StatusOfTask statusOfTask;
  private LocalDate dateFrom;
  private LocalDate dateTo;

  public SearchCriteria(User user,String statusOfTask, String dateFrom, String dateTo) {
    this.user = user;
    if(statusOfTask!= null) this.statusOfTask = StatusOfTask.valueOf(statusOfTask.toUpperCase());

    if(dateFrom == null) this.dateFrom = null;
    else this.dateFrom = stringToDate(dateFrom);

    if(dateTo == null) this.dateTo = null;
    else this.dateTo = stringToDate(dateTo);
  }

  LocalDate stringToDate(String date){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    return LocalDate.parse(date,formatter);

  }
}