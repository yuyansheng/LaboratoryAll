package com.sdut.laboratorybackmodule.controller;

import com.sdut.laboratorybackmodule.entity.Team;
import com.sdut.laboratorybackmodule.service.ITeamService;
import com.sdut.laboratorybackmodule.service.impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = "*")
public class TeamController {

    @Autowired
    private ITeamService teamService;

    @PostMapping
    public void addTeam(@RequestBody Team team) {
        teamService.saveTeam(team);
    }

    @PutMapping("/{id}")
    public void updateTeam(@PathVariable Long id, @RequestBody Team team) {
        team.setId(id);
        teamService.updateTeam(team);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeamById(id);
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return teamService.findTeamById(id);
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.findAllTeams();
    }
}
