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
@Entity(name = "tasks")
public class Task {
  @Id
  @Column(name = "Id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "header")
  private String header;
  @Column(name = "description")
  private String description;

//  @ManyToOne(targetEntity = User.class)
//  @JoinColumn(name="userId")
  private Long userId;

  @Column(name = "date")
  private LocalDate date;
  @Column(name = "status")
  private StatusOfTask status;

  @OneToMany(mappedBy="task",targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  List<Comment> commentList;

  @Override
  public String toString() {
    String formattedDate = this.getDate().format(DateTimeFormatter
            .ofPattern("dd.MM.yyyy"));
    return getId() + "," + getHeader() + "," + getDescription() + "," + getUserId()+ "," + formattedDate + "," + getStatus() + "\n";
  }
}
