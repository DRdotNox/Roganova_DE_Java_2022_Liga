package com.liga.homework.service;

import com.liga.homework.model.Project;

import java.util.List;

public interface ProjectService {
    void save(Project project);

    Project getOneProject(Long projectId);

    List<Project> getAllProjects();

    void deleteOneProject(Long projectId);

    void deleteAllProjects();
}
