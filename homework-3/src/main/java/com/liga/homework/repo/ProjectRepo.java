package com.liga.homework.repo;

import com.liga.homework.model.Project;
import com.liga.homework.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project,Long> {
}
