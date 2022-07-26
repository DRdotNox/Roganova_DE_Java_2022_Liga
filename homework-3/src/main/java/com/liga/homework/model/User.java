package com.liga.homework.model;

import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="Id")
  private Long id;

  @Column(name="Name")
  private String name;

  @OneToMany(mappedBy="user", targetEntity = Task.class, cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  List<Task> taskList;

  @Override
  public String toString() {
    return getId() + " , " + getName() + "\n";
  }

}
