package com.liga.homework.controller;

import com.liga.homework.model.Comment;
import com.liga.homework.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments/v1")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void addComment(@RequestBody(required=false) Comment comment){
        commentService.save(comment);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void editComment(@RequestBody (required=false) Comment comment){
        commentService.save(comment);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment getOneComment(@PathVariable("id") Long commenId){
        return commentService.getOneComment(commenId);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getAllComment(){
        return commentService.getAllComments();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOneComment(@PathVariable("id") Long commentId){
        commentService.deleteOneComment(commentId);
    }

    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllComments(){
        commentService.deleteAllComments();
    }
}
