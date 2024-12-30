package com.sdut.laboratorybackmodule.controller;

import com.sdut.laboratorybackmodule.entity.TeamMember;
import com.sdut.laboratorybackmodule.service.ITeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teamMembers")
@CrossOrigin(origins = "*")
public class TeamMemberController {

    @Autowired
    private ITeamMemberService teamMemberService;

    @PutMapping("/add")
    public void addTeamMember(@RequestBody TeamMember teamMember) {
        teamMemberService.addTeamMember(teamMember);
    }

    @DeleteMapping("/delete")
    public void deleteTeamMember(@RequestBody TeamMember teamMember) {
        teamMemberService.deleteTeamMember(teamMember);
    }

    @GetMapping("/members/{teamId}")
    public List<Long> getMembersByTeamId(@PathVariable Long teamId) {
        return teamMemberService.findMembersByTeamId(teamId);
    }

    @GetMapping("/teams")
    public List<Long> getTeamsByMemberId(@RequestParam("memberId") Long memberId) {
        return teamMemberService.findTeamsByMemberId(memberId);
    }
}