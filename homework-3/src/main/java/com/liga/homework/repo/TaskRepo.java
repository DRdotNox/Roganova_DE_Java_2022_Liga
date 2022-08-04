package com.liga.homework.repo;

import com.liga.homework.model.Task;
import java.util.List;

import com.liga.homework.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaskRepo extends JpaRepository<Task,Long>, JpaSpecificationExecutor<Task> {
  List<Task> findByUserId(Long userId);
}
