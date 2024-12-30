package com.sdut.laboratorybackmodule.controller;

import com.sdut.laboratorybackmodule.entity.Member;
import com.sdut.laboratorybackmodule.entity.Team;
import com.sdut.laboratorybackmodule.service.ITeamService;
import com.sdut.laboratorybackmodule.service.impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/team")
@CrossOrigin(origins = "*")
public class TeamController {

    @Autowired
    private ITeamService teamService;

    @PostMapping("/add")
    public Long addTeam(@RequestBody Team team) {
        return teamService.saveTeam(team);

    }

    @PutMapping("update/{id}")
    public void updateTeam(@PathVariable Long id, @RequestBody Team team) {
        team.setId(id);
        teamService.updateTeam(team);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeamById(id);
    }

    @GetMapping("get/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return teamService.findTeamById(id);
    }

    @GetMapping("/all")
    public List<Team> getAllTeams() {
        return teamService.findAllTeams();
    }

    @GetMapping("/members")
    public List<Map<String,Object>> getTeamMemberNameAndId(@RequestParam("teamId") Long teamId){
        List<Member> allMembers = teamService.findAllMembersByTeamId(teamId);
        List<Map<String,Object>> result = new ArrayList<>();
        for(Member member : allMembers){
            Map<String,Object> map = new HashMap<>();
            map.put("memberId",member.getId());
            map.put("name",member.getName());
            result.add(map);
        }
        return result;
    }
}
