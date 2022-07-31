package com.liga.homework.model;

import com.liga.homework.enums.StatusOfTask;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
@Entity
@Table(name = "tasks")
public class Task {
  @Id
  @Column(name = "Id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "header")
  private String header;
  @Column(name = "description")
  private String description;

  @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  @JoinColumn(name="user_id",referencedColumnName = "Id")
  private User user;

  @Column(name = "date")
  private LocalDate date;

  @Enumerated(EnumType.STRING)
  @Column(name="status",columnDefinition = "varchar(255) default 'EMPTY'")
  private StatusOfTask status;

  @OneToMany(mappedBy="task",targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  List<Comment> commentList;

  @Override
  public String toString() {
    String formattedDate = this.getDate().format(DateTimeFormatter
            .ofPattern("dd.MM.yyyy"));
    return getId() + "," + getHeader() + "," + getDescription() + "," + user.getId()+ "," + formattedDate + "," + getStatus();
  }
}
