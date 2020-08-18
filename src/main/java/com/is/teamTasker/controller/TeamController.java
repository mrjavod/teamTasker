package com.is.teamTasker.controller;

import com.is.teamTasker.entity.Status;
import com.is.teamTasker.entity.Team;
import com.is.teamTasker.model.AppResponse;
import com.is.teamTasker.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/team/")
public class TeamController {

    private TeamRepository teamRepository;

    @Autowired
    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    //@Secured("ROLE_MANAGER")
    @PostMapping("create")
    public ResponseEntity create(@RequestBody Team team) {

        teamRepository.save(new Team(team.getName(), team.getDescription(), team.getProjects(), Status.ACTIVE));

        return ResponseEntity.ok(new AppResponse(1, "Created"));
    }

    @PostMapping("addProject")
    public ResponseEntity addProject(@RequestBody Team team) {

        Team updatedTeam = teamRepository.findById(team.getId()).orElse(null);

        if (updatedTeam != null) {

            updatedTeam.setProjects(team.getProjects());
            teamRepository.save(updatedTeam);
            return ResponseEntity.ok(new AppResponse(1, "New project added"));
        }

        return ResponseEntity.ok(new AppResponse(0, "Team with current id not found"));
    }

    @GetMapping("getTeamsByProjectId/{project_id}")
    public ResponseEntity getTeams(@PathVariable(name = "project_id") Long projectId) {

        List<Team> teams = teamRepository.findAllByProjects_Id(projectId);

        return ResponseEntity.ok(new AppResponse(1, "OK", teams));
    }

}
