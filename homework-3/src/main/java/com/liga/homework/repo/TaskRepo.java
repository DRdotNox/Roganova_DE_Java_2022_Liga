package com.liga.homework.repo;

import com.liga.homework.model.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task,Long> {
  List<Task> findByUserId(Long userId);
}
