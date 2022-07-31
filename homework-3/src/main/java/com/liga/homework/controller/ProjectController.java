package com.liga.homework.controller;

import com.liga.homework.model.Project;

import com.liga.homework.service.ProjectService;
import com.liga.homework.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/v1")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void addProject(@RequestBody (required=false) Project project){
        projectService.save(project);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void editProject(@RequestBody (required=false) Project project){
        projectService.save(project);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project getOneProject(@PathVariable("id") Long projectId){
        return projectService.getOneProject(projectId);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOneProject(@PathVariable("id") Long projectId){
        projectService.deleteOneProject(projectId);
    }

    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllProjects(){
        projectService.deleteAllProjects();
    }
}
