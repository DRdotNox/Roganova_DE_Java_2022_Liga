package com.liga.homework.service.impl;

import com.liga.homework.model.Project;
import com.liga.homework.model.User;
import com.liga.homework.repo.ProjectRepo;
import com.liga.homework.repo.UserRepo;
import com.liga.homework.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepo projectRepo;
    private final UserRepo userRepo;

    @Transactional
    @Override
    public void save(Project project) {
        if(project == null) {
            project = Project.builder()
                    .header("Нет заголовка")
                    .description("Нет описания")
                    .build();
        }
        else {
            if(project.getHeader() == null) project.setHeader("Нет заголовка");
            if(project.getDescription() == null) project.setDescription("Нет описания");
            if(project.getUsers()!=null) {
                Set<User> userSet = new HashSet<>();
                project.getUsers().forEach(u -> {
                    User user =  userRepo.findById(u.getId()).orElseThrow(EntityNotFoundException::new);
                    userSet.add(user);
                });
                project.setUsers(userSet);
            }
        }
        projectRepo.save(project);
    }

    @Override
    public Project getOneProject(Long projectId) {
        return projectRepo.findById(projectId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    @Transactional
    @Override
    public void deleteOneProject(Long projectId) {
        projectRepo.deleteById(projectId);
    }

    @Transactional
    @Override
    public void deleteAllProjects() {
        projectRepo.deleteAll();
    }
}
