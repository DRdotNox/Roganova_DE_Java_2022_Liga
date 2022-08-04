package com.liga.homework.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "projects")
public class Project {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "header")
    private String header;
    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "projects")
    private Set<User> users;

}
