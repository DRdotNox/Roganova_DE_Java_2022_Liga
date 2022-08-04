package com.liga.homework.repo;

import com.liga.homework.model.Comment;
import com.liga.homework.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Long> {
}
