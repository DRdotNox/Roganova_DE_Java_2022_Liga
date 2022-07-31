package com.liga.homework.service.impl;

import com.liga.homework.model.Comment;
import com.liga.homework.model.Project;
import com.liga.homework.repo.CommentRepo;
import com.liga.homework.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;

    @Transactional
    @Override
    public void save(Comment comment) {
        commentRepo.save(comment);
    }

    @Override
    public Comment getOneComment(Long commentId) {
        return commentRepo.findById(commentId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }

    @Transactional
    @Override
    public void deleteOneComment(Long commentId) {
        commentRepo.deleteById(commentId);
    }

    @Transactional
    @Override
    public void deleteAllComments() {
        commentRepo.deleteAll();
    }
}
