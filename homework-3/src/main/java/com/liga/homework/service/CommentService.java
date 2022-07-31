package com.liga.homework.service;

import com.liga.homework.model.Comment;

import java.util.List;

public interface CommentService {
    void save(Comment comment);

    Comment getOneComment(Long commentId);

    List<Comment> getAllComments();

    void deleteOneComment(Long commentId);

    void deleteAllComments();
}
