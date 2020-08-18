package com.is.teamTasker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.is.teamTasker.entity.Project;
import com.is.teamTasker.entity.Status;
import com.is.teamTasker.entity.User;
import com.is.teamTasker.model.AppResponse;
import com.is.teamTasker.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resources;
import java.util.List;

@RestController
@RequestMapping("api/v1/project/")
public class ProjectController {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Secured("ROLE_MANAGER")
    @PostMapping("create")
    public ResponseEntity create(@RequestBody Project project) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        projectRepository.save(new Project(project.getName(), project.getDescription(), user, Status.ACTIVE));

        return ResponseEntity.ok(new AppResponse(1, "Created"));
    }

    //    @Secured("ROLE_MANAGER")
    @GetMapping("getAll")
    public ResponseEntity getAll() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        List<Project> projects = projectRepository.getAllProjectsByUserId(user);

        return ResponseEntity.ok(new AppResponse(1, "OK", projects));
    }

    @GetMapping("findAll")
    public ResponseEntity findAll() throws JsonProcessingException {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        List<Project> projects = projectRepository.getAllProjectsByUserId(user);

        return ResponseEntity.ok(new AppResponse(1, "OK", projects));
    }
}
