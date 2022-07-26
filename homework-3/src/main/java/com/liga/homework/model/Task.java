package com.liga.homework.model;

import com.liga.homework.enums.StatusOfTask;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.*;

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
  @Column(name="Id")
  private Long id;

  @Column(name="Header")
  private String header;
  @Column(name="Description")
  private String description;

  @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY,targetEntity = User.class)
  @JoinColumn(name="user_id",referencedColumnName = "Id")
  private User user;

  @Column(name="Deadline")
  private LocalDate date;

  @Enumerated(EnumType.STRING)
  @Column(name="Status",columnDefinition = "varchar(255) default 'EMPTY'")
  private StatusOfTask status;


  @Override
  public String toString() {
    String formattedDate = this.getDate().format(DateTimeFormatter
            .ofPattern("dd.MM.yyyy"));
    return getId() + "," + getHeader() + "," + getDescription() + "," + user.getName()+ "," + formattedDate + "," + getStatus() + "\n";
  }
}
