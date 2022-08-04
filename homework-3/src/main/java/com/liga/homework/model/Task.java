package com.liga.homework.model;

import com.liga.homework.enums.StatusOfTask;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
  @Id
  @Column(name = "Id")
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

  @OneToMany(mappedBy="task",targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  List<Comment> commentList;


  @Override
  public String toString() {
    String formattedDate = this.getDate().format(DateTimeFormatter
            .ofPattern("dd.MM.yyyy"));
    return getId() + "," + getHeader() + "," + getDescription() + "," + user.getName()+ "," + formattedDate + "," + getStatus() + "\n";

  }
}
