package com.liga.homework.model;

import com.liga.homework.enums.StatusOfTask;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "tasks")
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String header;
  private String description;

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name="userId")
  private Long userId;

  private LocalDate date;
  private StatusOfTask status;

  @Override
  public String toString() {
    String formattedDate = this.getDate().format(DateTimeFormatter
            .ofPattern("dd.MM.yyyy"));
    return getId() + "," + getHeader() + "," + getDescription() + "," + getUserId()+ "," + formattedDate + "," + getStatus() + "\n";
  }
}
